package com.ej.unit;

import com.ej.HexagonalMusahebeApplication;
import com.ej.adapter.bill.jpa.BillDataAdapter;
import com.ej.adapter.bill.jpa.repo.BillRepository;
import com.ej.adapter.seller.jpa.repo.SellerRepository;
import com.ej.bill.model.BillModel;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.enums.BillStatus;
import com.ej.common.exception.BillNoAlreadyExistException;
import com.ej.common.exception.SellerNotFoundException;
import com.ej.seller.model.SellerModel;
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
@SpringBootTest(classes=HexagonalMusahebeApplication.class)
class BillDataAdapterTest {
    @InjectMocks
    private BillDataAdapter billDataAdapter;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BillRepository billRepository;

    @BeforeEach
    public void setup() {

        billDataAdapter = new BillDataAdapter(billRepository,sellerRepository);
        billDataAdapter.amountLimit=200;

    }

    @Test
    void should_accept_bill_when_under_limit(){
        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TEST999")
                .amount(1)
                .sellerEmail("sercan@celenk.com")
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .productName("Test")
                .build();

        BillModel expectedBillModel = BillModel.builder()
                .billStatus(BillStatus.ACCEPTED)
                .billId(5)
                .productName("Test")
                .amount(1)
                .seller(SellerModel.builder()
                        .sellerEmail("sercan@celenk.com")
                        .sellerSurname("Çelenk")
                        .sellerName("Sercan")
                        .sellerId(3)
                        .build())
                .billNo("TEST999")
                .build();


        //when
        final var createdBill=billDataAdapter.createBill(createBillUseCase);

        //then
        Assertions.assertEquals(expectedBillModel.getBillNo(),createdBill.getBillNo());
        Assertions.assertEquals(expectedBillModel.getBillStatus(),createdBill.getBillStatus());
        Assertions.assertEquals(expectedBillModel.getAmount(),createdBill.getAmount());
        Assertions.assertEquals(expectedBillModel.getProductName(),createdBill.getProductName());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerId(),createdBill.getSeller().getSellerId());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerName(),createdBill.getSeller().getSellerName());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerSurname(),createdBill.getSeller().getSellerSurname());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerEmail(),createdBill.getSeller().getSellerEmail());


    }

    @Test
    void should_deny_bill_when_over_limit(){
        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TEST9997")
                .amount(201)
                .sellerEmail("sercan@celenk.com")
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .productName("Test")
                .build();

        BillModel expectedBillModel = BillModel.builder()
                .billStatus(BillStatus.DENIED)
                .billId(5)
                .productName("Test")
                .amount(201)
                .seller(SellerModel.builder()
                        .sellerEmail("sercan@celenk.com")
                        .sellerSurname("Çelenk")
                        .sellerName("Sercan")
                        .sellerId(3)
                        .build())
                .billNo("TEST9997")
                .build();

        //when
        final var createdBill=billDataAdapter.createBill(createBillUseCase);

        //then
        Assertions.assertEquals(expectedBillModel.getBillNo(),createdBill.getBillNo());
        Assertions.assertEquals(expectedBillModel.getBillStatus(),createdBill.getBillStatus());
        Assertions.assertEquals(expectedBillModel.getAmount(),createdBill.getAmount());
        Assertions.assertEquals(expectedBillModel.getProductName(),createdBill.getProductName());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerId(),createdBill.getSeller().getSellerId());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerName(),createdBill.getSeller().getSellerName());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerSurname(),createdBill.getSeller().getSellerSurname());
        Assertions.assertEquals(expectedBillModel.getSeller().getSellerEmail(),createdBill.getSeller().getSellerEmail());


    }

    @Test
    void should_throw_exception_when_bill_already_exist(){

        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TR000")
                .amount(1)
                .sellerEmail("sercan@celenk.com")
                .sellerName("Sercan")
                .sellerSurname("Çelenk")
                .productName("Test")
                .build();

        //when

        //then
        Assertions.assertThrows(BillNoAlreadyExistException.class,()-> billDataAdapter.createBill(createBillUseCase));

    }

    @Test
    void should_throw_exception_when_seller_not_exist() {

        //given
        CreateBillUseCase createBillUseCase = CreateBillUseCase.builder()
                .billNo("TEST999")
                .amount(1)
                .sellerEmail("aaaa")
                .sellerName("aaa")
                .sellerSurname("aaa")
                .productName("Test")
                .build();

        //when

        //then
        Assertions.assertThrows(SellerNotFoundException.class,()-> billDataAdapter.createBill(createBillUseCase));

    }

    @Test
    void should_get_accepted_bills(){
        //given
        GetBillByStatusUseCase getBillByStatusUseCase = GetBillByStatusUseCase.builder()
                .billStatus(BillStatus.ACCEPTED).build();

        //when
        final var acceptedBills=billDataAdapter.getBillsByStatus(getBillByStatusUseCase);

        //then
        Assertions.assertEquals(BillStatus.ACCEPTED,acceptedBills.get(0).getBillStatus());
        Assertions.assertEquals(BillStatus.ACCEPTED,acceptedBills.get(1).getBillStatus());

    }

    @Test
    void should_get_denied_bills(){
        //given
        GetBillByStatusUseCase getBillByStatusUseCase = GetBillByStatusUseCase.builder()
                .billStatus(BillStatus.DENIED).build();

        //when
        final var deniedBills=billDataAdapter.getBillsByStatus(getBillByStatusUseCase);

        //then
        Assertions.assertEquals(BillStatus.DENIED,deniedBills.get(0).getBillStatus());
        Assertions.assertEquals(BillStatus.DENIED,deniedBills.get(1).getBillStatus());

    }

}
