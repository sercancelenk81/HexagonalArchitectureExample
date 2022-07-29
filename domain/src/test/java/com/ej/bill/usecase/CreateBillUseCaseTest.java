package com.ej.bill.usecase;

import com.ej.seller.model.SellerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBillUseCaseTest {
    @Test
    void should_create() {
        //given
        String billNo = "TR999";
        Integer amount = 100;
        String productName = "Lamp";
        String sellerEmail = "sercan@celenk.com";
        String sellerSurname = "Çelenk";
        String sellerName = "Sercan";

        //when
        final var createBillUseCase = new CreateBillUseCase(sellerName, sellerSurname, sellerEmail, amount, productName, billNo);

        //then
        Assertions.assertEquals(billNo,createBillUseCase.getBillNo());
        Assertions.assertEquals(amount,createBillUseCase.getAmount());
        Assertions.assertEquals(productName,createBillUseCase.getProductName());
        Assertions.assertEquals(sellerName,createBillUseCase.getSellerName());
        Assertions.assertEquals(sellerSurname,createBillUseCase.getSellerSurname());
        Assertions.assertEquals(sellerEmail,createBillUseCase.getSellerEmail());
    }
}
