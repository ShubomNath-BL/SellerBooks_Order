package com.example.orderdetails.services;

import com.example.orderdetails.dto.OrderDTO;
import com.example.orderdetails.entity.OrderDetails;
import com.example.orderdetails.exception.OrderException;

public interface IOrderService {
    String placeOrder(OrderDTO orderDTO) throws OrderException;

    OrderDetails findByTokens(String token) throws OrderException;

    OrderDetails updateOrder(OrderDTO orderDTO, int id) throws OrderException;

    OrderDetails updateCancelStatus(OrderDTO orderDTO, int id) throws OrderException;

    OrderDetails updateDispatchStatus(OrderDTO orderDTO, int id) throws OrderException;

    void deleteOrder(OrderDTO orderDTO, int id) throws OrderException;
}
