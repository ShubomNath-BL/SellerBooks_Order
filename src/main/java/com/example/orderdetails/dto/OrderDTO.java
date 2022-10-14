package com.example.orderdetails.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDTO {
    private LocalDate orderDate;
    private int price;
    private int quantity;
    private int userId;
    private int bookId;
    private boolean cancel;
    private boolean dispatch;
}
