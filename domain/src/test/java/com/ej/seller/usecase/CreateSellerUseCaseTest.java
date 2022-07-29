package com.ej.seller.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateSellerUseCaseTest {
    @Test
    void should_create(){
        //given
        String sellerName ="Sercan";
        String sellerSurname="Çelenk";
        String sellerEmail="sercan@celenk.com";

        //when
        final var createSellerUseCase = CreateSellerUseCase.builder()
                .sellerEmail(sellerEmail)
                .sellerName(sellerName)
                .sellerSurname(sellerSurname)
                .build();

        //then
        Assertions.assertEquals(sellerEmail,createSellerUseCase.getSellerEmail());
        Assertions.assertEquals(sellerName,createSellerUseCase.getSellerName());
        Assertions.assertEquals(sellerSurname,createSellerUseCase.getSellerSurname());
    }

}
