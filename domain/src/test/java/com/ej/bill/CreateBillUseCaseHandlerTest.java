package com.ej.bill;

import com.ej.bill.port.BillFakeDataPort;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.common.enums.BillStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateBillUseCaseHandlerTest {
    CreateBillUseCaseHandler createBillUseCaseHandler;

    @BeforeEach
    void setUp(){
        createBillUseCaseHandler = new CreateBillUseCaseHandler(new BillFakeDataPort());
    }

    @Test
    void should_create_bill(){
        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TR999")
                .amount(50)
                .productName("Test test")
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .sellerEmail("sercan@celenk.com")
                .build();

        //when
        var billModel = createBillUseCaseHandler.handle(createBillUseCase);

        //then
        assertEquals(3,billModel.getSeller().getSellerId());
        assertEquals("Sercan",billModel.getSeller().getSellerName());
        assertEquals("Çelenk",billModel.getSeller().getSellerSurname());
        assertEquals("Test test",billModel.getProductName());
    }

    @Test
    void should_accept_bill(){
        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TR999")
                .amount(50)
                .productName("Test test")
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .sellerEmail("sercan@celenk.com")
                .build();

        //when
        var billModel = createBillUseCaseHandler.handle(createBillUseCase);

        //then
        assertEquals(3,billModel.getSeller().getSellerId());
        assertEquals("Sercan",billModel.getSeller().getSellerName());
        assertEquals(BillStatus.ACCEPTED,billModel.getBillStatus());
    }

    @Test
    void should_deny_bill(){
        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TR999")
                .amount(90)
                .productName("Test test")
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .sellerEmail("sercan@celenk.com")
                .build();

        //when
        var billModel = createBillUseCaseHandler.handle(createBillUseCase);

        //then
        assertEquals(3,billModel.getSeller().getSellerId());
        assertEquals("Sercan",billModel.getSeller().getSellerName());
        assertEquals(BillStatus.DENIED,billModel.getBillStatus());
    }
}
