package com.example.orderdetails.entity;

import com.example.orderdetails.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    private int id;
    private LocalDate orderDate;
    private int price;
    private int quantity;
    private int userId;
    private int bookId;
    private boolean cancel;
    private boolean dispatch;

    public OrderDetails(OrderDTO orderDTO) {
        this.orderDate = orderDTO.getOrderDate();
        this.price = orderDTO.getPrice();
        this.quantity = orderDTO.getQuantity();
        this.userId = orderDTO.getUserId();
        this.bookId = orderDTO.getBookId();
        this.cancel = orderDTO.isCancel();
        this.dispatch = orderDTO.isDispatch();
    }
}
