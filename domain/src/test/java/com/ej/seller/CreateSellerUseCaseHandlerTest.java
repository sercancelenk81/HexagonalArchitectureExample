package com.ej.seller;

import com.ej.seller.port.SellerFakeDataPort;
import com.ej.seller.usecase.CreateSellerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class CreateSellerUseCaseHandlerTest {
    CreateSellerUseCaseHandler createSellerUseCaseHandler;

    @BeforeEach
    void setUp(){
        createSellerUseCaseHandler = new CreateSellerUseCaseHandler(new SellerFakeDataPort());
    }

    @Test
    void should_create_seller(){
        //given
        CreateSellerUseCase createSellerUseCase = CreateSellerUseCase.builder()
                .sellerName("Tester")
                .sellerSurname("Sercan")
                .sellerEmail("tester@sercan.com")
                .build();

        //when
        var sellerModel = createSellerUseCaseHandler.handle(createSellerUseCase);

        //then
        assertEquals(createSellerUseCase.getSellerName(),sellerModel.getSellerName());
        assertEquals(createSellerUseCase.getSellerSurname(),sellerModel.getSellerSurname());
        assertEquals(createSellerUseCase.getSellerEmail(),sellerModel.getSellerEmail());

    }

}
