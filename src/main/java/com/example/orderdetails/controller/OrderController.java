package com.example.orderdetails.controller;

import com.example.orderdetails.dto.OrderDTO;
import com.example.orderdetails.dto.ResponseDTO;
import com.example.orderdetails.entity.OrderDetails;
import com.example.orderdetails.exception.OrderException;
import com.example.orderdetails.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService service;

    @PostMapping("/register")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) throws Exception, OrderException {
        String token = service.placeOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Record added successfully", token);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/findByToken/{token}")
    public ResponseEntity<ResponseDTO> findByToken(@PathVariable String token) throws OrderException {
        OrderDetails orderDetails = service.findByTokens(token);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved by id successfully:-", orderDetails);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateOrderById(@RequestBody OrderDTO orderDTO, @PathVariable int id) throws OrderException {
        OrderDetails orderDetails = service.updateOrder(orderDTO, id);
        ResponseDTO responseDTO = new ResponseDTO("Orders after updated are:- ", orderDetails);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/updateCancelStatus/{id}")
    public ResponseEntity<ResponseDTO> updateCancelStatus(@RequestBody OrderDTO orderDTO, @PathVariable int id) throws OrderException{
        OrderDetails orderDetails = service.updateCancelStatus(orderDTO, id);
        ResponseDTO responseDTO = new ResponseDTO("Orders is cancelled:- ", orderDetails);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/updateDispatchStatus/{id}")
    public ResponseEntity<ResponseDTO> updateDispatchStatus(@RequestBody OrderDTO orderDTO, @PathVariable int id) throws OrderException{
        OrderDetails orderDetails = service.updateDispatchStatus(orderDTO, id);
        ResponseDTO responseDTO = new ResponseDTO("Orders is dispatched:- ", orderDetails);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder(@RequestBody OrderDTO orderDTO, @PathVariable int id) throws OrderException{
        service.deleteOrder(orderDTO, id);
        ResponseDTO responseDTO = new ResponseDTO("Orders is deleted:- ", "Deleted id: "+id);
        return new ResponseEntity(responseDTO, HttpStatus.GONE);
    }
    @GetMapping("/pendingOrders")
    public ResponseEntity<ResponseDTO> pendingOrder() throws OrderException{
        List<OrderDetails> orderDetails = service.getPendingOrders();
        ResponseDTO responseDTO = new ResponseDTO("Pending orders are:- ", orderDetails);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
