package com.ej.seller.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SellerModelTest {
    @Test
    void should_create(){
    //given
    Integer sellerId = 3;
    String sellerName = "Sercan";
    String sellerSurname = "Çelenk";
    String sellerEmail = "sercan@celenk.com";

    //when
    final var sellerModel = SellerModel.builder()
            .sellerId(sellerId)
            .sellerName(sellerName)
            .sellerSurname(sellerSurname)
            .sellerEmail(sellerEmail)
            .build();

    //then
        Assertions.assertEquals(sellerName,sellerModel.getSellerName());
        Assertions.assertEquals(sellerSurname,sellerModel.getSellerSurname());
        Assertions.assertEquals(sellerId,sellerModel.getSellerId());
        Assertions.assertEquals(sellerEmail,sellerModel.getSellerEmail());
    }
}
