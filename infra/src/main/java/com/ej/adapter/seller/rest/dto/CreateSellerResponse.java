package com.ej.adapter.seller.rest.dto;

import com.ej.seller.model.SellerModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSellerResponse {
    private Integer sellerId;
    private String sellerName;
    private String sellerSurname;
    private String sellerEmail;

    public static CreateSellerResponse from(SellerModel sellerModel) {
        return CreateSellerResponse.builder()
                .sellerId(sellerModel.getSellerId())
                .sellerName(sellerModel.getSellerName())
                .sellerSurname(sellerModel.getSellerSurname())
                .sellerEmail(sellerModel.getSellerEmail())
                .build();
    }
}
