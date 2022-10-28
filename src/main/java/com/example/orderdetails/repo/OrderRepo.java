package com.example.orderdetails.repo;

import com.example.orderdetails.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, Integer> {
    @Query(value = "SELECT * FROM booksellerorder.order_details where cancel=false and dispatch=false", nativeQuery = true)
    List<OrderDetails> findPendingOrders();
}
