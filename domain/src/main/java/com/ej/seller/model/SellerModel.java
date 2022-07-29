package com.ej.seller.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SellerModel {
    private Integer sellerId;

    private String sellerName;

    private String sellerSurname;

    private String sellerEmail;
}
