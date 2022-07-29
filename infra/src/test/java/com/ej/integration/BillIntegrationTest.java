package com.ej.integration;

import com.ej.HexagonalMusahebeApplication;
import com.ej.adapter.bill.rest.dto.CreateBillRequest;
import com.ej.adapter.bill.rest.dto.CreateBillResponse;
import com.ej.adapter.bill.rest.dto.GetBillByStatusResponse;
import com.ej.common.enums.BillStatus;
import com.ej.common.result.DataResult;
import com.ej.common.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@Sql(scripts = "/clean-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(classes = HexagonalMusahebeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BillIntegrationTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    void should_create_bill(){
        //given
        CreateBillRequest createBillRequest = CreateBillRequest.builder()
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .sellerEmail("sercan@celenk.com")
                .amount(1)
                .productName("Test")
                .billNo("Test9995").build();

        //when
        var responseEntity= restTemplate.postForEntity("/api/bills/create",createBillRequest, CreateBillResponse.class);

        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
    }

    @Test
    void should_deny_bill_when_over_limit(){
        //given
        CreateBillRequest createBillRequest = CreateBillRequest.builder()
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .sellerEmail("sercan@celenk.com")
                .amount(201)
                .productName("Test")
                .billNo("Test9995").build();

        //when
        var responseEntity= restTemplate.postForEntity("/api/bills/create",createBillRequest, CreateBillResponse.class);

        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(400,responseEntity.getStatusCodeValue());
    }

    @Test
    void should_return_error_when_bill_no_already_exist(){
        //given
        CreateBillRequest createBillRequest = CreateBillRequest.builder()
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .sellerEmail("sercan@celenk.com")
                .amount(1)
                .productName("Test")
                .billNo("TR000").build();

        //when
        var responseEntity= restTemplate.postForEntity("/api/bills/create",createBillRequest, CreateBillResponse.class);

        //then
        Assertions.assertEquals(500,responseEntity.getStatusCodeValue());
    }

    @Test
    void should_get_accepted_bills(){
        //given

        //when
        var responseEntity= restTemplate.getForEntity("/api/bills/get-bills/accepted", List.class);

        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(4,responseEntity.getBody().size());
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());

    }

    @Test
    void should_get_denied_bills(){
        //given

        //when
        var responseEntity= restTemplate.getForEntity("/api/bills/get-bills/denied",List.class);

        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(2,responseEntity.getBody().size());
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());

    }

}
