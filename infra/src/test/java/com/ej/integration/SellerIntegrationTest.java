package com.ej.integration;

import com.ej.HexagonalMusahebeApplication;
import com.ej.adapter.seller.rest.dto.CreateSellerRequest;
import com.ej.adapter.seller.rest.dto.CreateSellerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@Sql(scripts = "/clean-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(classes = HexagonalMusahebeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellerIntegrationTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    void should_create_seller(){
        //given
        CreateSellerRequest createSellerRequest=CreateSellerRequest.builder()
                .sellerSurname("Test")
                .sellerName("Test Name")
                .sellerEmail("create@test.com")
                .build();

        //when
        var responseEntity= restTemplate.postForEntity("/api/sellers/create",createSellerRequest, CreateSellerResponse.class);

        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
    }
}
