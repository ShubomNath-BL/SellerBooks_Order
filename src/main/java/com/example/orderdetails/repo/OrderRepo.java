package com.example.orderdetails.repo;

import com.example.orderdetails.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, Integer> {
}
