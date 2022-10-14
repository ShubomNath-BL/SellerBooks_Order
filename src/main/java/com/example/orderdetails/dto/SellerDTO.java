package com.example.orderdetails.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SellerDTO {
    private Long id;
    private String businessName;
    private String sellerName;
    private String gstn;
    private String sellerWebsite;
    private String emailAddress;
    private String userName;
    private String password;
    private String contactNumber;
    private boolean verify;
    private LocalDateTime createdTimeStamp;
    private LocalDateTime updatedTimeStamp;
}
