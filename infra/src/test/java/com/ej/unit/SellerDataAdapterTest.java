package com.ej.unit;

import com.ej.HexagonalMusahebeApplication;
import com.ej.adapter.seller.jpa.SellerDataAdapter;
import com.ej.adapter.seller.jpa.repo.SellerRepository;
import com.ej.common.exception.SellerAlreadyExistException;
import com.ej.seller.model.SellerModel;
import com.ej.seller.usecase.CreateSellerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@Sql(scripts = "/clean-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(classes= HexagonalMusahebeApplication.class)

class SellerDataAdapterTest {
    @InjectMocks
    private SellerDataAdapter sellerDataAdapter;
    @Autowired
    private SellerRepository sellerRepository;

    @BeforeEach
    public void setup() {
        sellerDataAdapter = new SellerDataAdapter(sellerRepository);
    }

    @Test
    void should_create_seller(){
        //given
        CreateSellerUseCase createSellerUseCase = CreateSellerUseCase.builder()
                .sellerSurname("Test")
                .sellerName("Test Name")
                .sellerEmail("create@test.com")
                .build();

        SellerModel expectedSeller= SellerModel.builder()
                .sellerName("Test Name")
                .sellerSurname("Test")
                .sellerEmail("create@test.com")
                .sellerId(99)
                .build();

        //when
        final var createdSeller=sellerDataAdapter.createSeller(createSellerUseCase);

        //then
        Assertions.assertEquals(expectedSeller.getSellerEmail(),createdSeller.getSellerEmail());
        Assertions.assertEquals(expectedSeller.getSellerName(),createdSeller.getSellerName());
        Assertions.assertEquals(expectedSeller.getSellerSurname(),createdSeller.getSellerSurname());
    }

    @Test
    void should_throw_exception_when_seller_already_exist(){
        //given
        CreateSellerUseCase createSellerUseCase = CreateSellerUseCase.builder()
                .sellerSurname("Test")
                .sellerName("Test Name")
                .sellerEmail("test@test.com")
                .build();

        //when

        //then
        Assertions.assertThrows(SellerAlreadyExistException.class,()->sellerDataAdapter.createSeller(createSellerUseCase));
    }
}

