package com.ej.seller.port;

import com.ej.seller.model.SellerModel;
import com.ej.seller.usecase.CreateSellerUseCase;

public class SellerFakeDataPort implements SellerDataPort {
    @Override
    public SellerModel createSeller(CreateSellerUseCase useCase) {
        return SellerModel.builder()
                .sellerId(4)
                .sellerName(useCase.getSellerName())
                .sellerSurname(useCase.getSellerSurname())
                .sellerEmail(useCase.getSellerEmail())
                .build();
    }
}
