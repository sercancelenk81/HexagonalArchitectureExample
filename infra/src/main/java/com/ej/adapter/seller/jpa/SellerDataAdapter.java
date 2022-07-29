package com.ej.adapter.seller.jpa;

import com.ej.adapter.seller.jpa.entity.SellerEntity;
import com.ej.adapter.seller.jpa.repo.SellerRepository;
import com.ej.common.exception.SellerAlreadyExistException;
import com.ej.seller.model.SellerModel;
import com.ej.seller.port.SellerDataPort;
import com.ej.seller.usecase.CreateSellerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
@RequiredArgsConstructor

public class SellerDataAdapter implements SellerDataPort {
    private final SellerRepository sellerRepository;

    public SellerModel createSeller(CreateSellerUseCase sellerUseCase){
        if(Objects.nonNull(sellerRepository.getSellerByEmail(sellerUseCase.getSellerEmail()))){
            throw new SellerAlreadyExistException(sellerUseCase.getSellerEmail());
        }

        final var sellerEntity= SellerEntity.builder()
                .firstName(sellerUseCase.getSellerName())
                .lastName(sellerUseCase.getSellerSurname())
                .email(sellerUseCase.getSellerEmail())
                .build();


        return sellerRepository.save(sellerEntity).toModel();

    }
}
