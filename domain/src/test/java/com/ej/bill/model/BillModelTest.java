package com.ej.bill.model;

import com.ej.common.enums.BillStatus;
import com.ej.seller.model.SellerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BillModelTest {
    @Test
    void should_create(){
        //given
        Integer billId= 99;
        String billNo= "TR999";
        Integer amount = 100;
        BillStatus billStatus= BillStatus.ACCEPTED;
        String productName = "Lamp";
        SellerModel seller= SellerModel.builder()
                .sellerEmail("sercan@celenk.com")
                .sellerSurname("Çelenk")
                .sellerName("Sercan")
                .sellerId(3)
                .build();

        //when
        final var billModel = BillModel.builder()
                .billId(billId)
                .seller(seller)
                .amount(amount)
                .productName(productName)
                .billNo(billNo)
                .billStatus(billStatus)
                .build();

        //then
        Assertions.assertEquals(billId,billModel.getBillId());
        Assertions.assertEquals(productName,billModel.getProductName());
        Assertions.assertEquals(amount,billModel.getAmount());
        Assertions.assertEquals(billStatus,billModel.getBillStatus());
        Assertions.assertEquals(billNo,billModel.getBillNo());
        Assertions.assertEquals(seller.getSellerName(),billModel.getSeller().getSellerName());
    }
}
