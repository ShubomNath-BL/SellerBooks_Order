package com.example.orderdetails.dto;

import com.example.orderdetails.entity.OrderDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object obj;

    public ResponseDTO(String message, String token) {
        this.message = message;
        this.obj = token;
    }

    public ResponseDTO(String message, OrderDetails orderDetails) {
        this.message = message;
        this.obj = orderDetails;
    }

    public ResponseDTO(String message, List<OrderDetails> orderDetails) {
        this.message = message;
        this.obj = orderDetails;
    }
}
