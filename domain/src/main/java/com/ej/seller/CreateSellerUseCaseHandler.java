package com.ej.seller;

import com.ej.common.DomainComponent;
import com.ej.common.UseCaseHandler;
import com.ej.seller.model.SellerModel;
import com.ej.seller.port.SellerDataPort;
import com.ej.seller.usecase.CreateSellerUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainComponent
public class CreateSellerUseCaseHandler implements UseCaseHandler<SellerModel, CreateSellerUseCase> {
    private final SellerDataPort sellerDataPort;
    @Override
    public SellerModel handle(CreateSellerUseCase useCase) {
        return sellerDataPort.createSeller(useCase);
    }
}
