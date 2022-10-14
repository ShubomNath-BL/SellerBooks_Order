package com.example.orderdetails.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderException extends Throwable {
    private String message;

    public OrderException(String message){
        this.message = message;
    }
}
