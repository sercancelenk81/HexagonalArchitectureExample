package com.ej.adapter.seller.rest.dto;

import com.ej.seller.usecase.CreateSellerUseCase;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSellerRequest {
    private String sellerName;
    private String sellerSurname;
    private String sellerEmail;

    public CreateSellerUseCase toUseCase() {
        return CreateSellerUseCase.builder()
                .sellerName(sellerName)
                .sellerEmail(sellerEmail)
                .sellerSurname(sellerSurname)
                .build();
    }
}
