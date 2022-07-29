package com.ej.seller.port;
import com.ej.seller.model.SellerModel;
import com.ej.seller.usecase.CreateSellerUseCase;

public interface SellerDataPort {
    SellerModel createSeller(CreateSellerUseCase useCase);
}
