package com.example.orderdetails.services;

import com.example.orderdetails.dto.OrderDTO;
import com.example.orderdetails.dto.User;
import com.example.orderdetails.entity.OrderDetails;
import com.example.orderdetails.exception.OrderException;
import com.example.orderdetails.repo.OrderRepo;
import com.example.orderdetails.util.EmailSenderService;
import com.example.orderdetails.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepo repo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService senderService;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String placeOrder(OrderDTO orderDTO) throws OrderException {
        User userData = restTemplate.getForObject("http://localhost:8083/user/id/"+orderDTO.getUserId(),User.class);
        Object bookData = restTemplate.getForObject("http://localhost:8081/books/id/"+orderDTO.getBookId(),Object.class);
        System.out.println(userData.getEmail());
        if(userData.equals(null) || bookData.equals(null)){
            throw new OrderException("User not found.....!");
        }else {
            OrderDetails orderDetails = new OrderDetails(orderDTO);
            repo.save(orderDetails);
            String token = tokenUtil.createToken(orderDetails.getId());
            senderService.sendEmail(userData.getEmail(),"TestMail...!","Hello..."+userData.getFirstName()+" http://localhost:8080/findByToken/"+token);
            return token;
        }
    }

    @Override
    public OrderDetails findByTokens(String token) throws OrderException {
        int id = tokenUtil.decodeToken(token);
        Optional<OrderDetails> orderDetails = repo.findById(id);
        if(orderDetails.isPresent()){
            return orderDetails.get();
        }else {
            throw new OrderException("ID not found");
        }
    }

    @Override
    public OrderDetails updateOrder(OrderDTO orderDTO, int id) throws OrderException {
        OrderDetails orderDetails = repo.findById(id).orElse(null);
        if(orderDetails!=null){
            orderDetails.setOrderDate(orderDTO.getOrderDate());
            orderDetails.setPrice(orderDTO.getPrice());
            orderDetails.setQuantity(orderDTO.getQuantity());
            orderDetails.setUserId(orderDTO.getUserId());
            orderDetails.setBookId(orderDTO.getBookId());
            repo.save(orderDetails);
            return orderDetails;
        }else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public OrderDetails updateCancelStatus(OrderDTO orderDTO, int id) throws OrderException {
        OrderDetails orderDetails = repo.findById(id).orElse(null);
        if(orderDetails!=null){
            orderDetails.setCancel(orderDTO.isCancel());
            repo.save(orderDetails);
            return orderDetails;
        }else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public OrderDetails updateDispatchStatus(OrderDTO orderDTO, int id) throws OrderException {
        OrderDetails orderDetails = repo.findById(id).orElse(null);
        if(orderDetails!=null){
            orderDetails.setDispatch(orderDTO.isDispatch());
            repo.save(orderDetails);
            return orderDetails;
        }else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public void deleteOrder(OrderDTO orderDTO, int id) throws OrderException {
        Optional<OrderDetails> orderDetails = repo.findById(id);
        if(orderDetails.isPresent()){
            repo.deleteById(id);
        }else {
            throw new OrderException("Order not found");
        }
    }
}
