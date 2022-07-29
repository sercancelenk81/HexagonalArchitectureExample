package com.ej.bill.port;

import com.ej.bill.model.BillModel;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.enums.BillStatus;
import com.ej.seller.model.SellerModel;

import java.util.List;

public class BillFakeDataPort implements BillDataPort {
    private final Integer FAKE_BILL_ID = 99;
    private final Integer FAKE_SELLER_ID = 3;
    private final Integer SERCAN_TOTAL = 120;

    private final Integer LIMIT = 200;

    @Override
    public BillModel createBill(CreateBillUseCase useCase) {
        if(SERCAN_TOTAL + useCase.getAmount() <= LIMIT) {
            return BillModel.builder()
                    .billNo(useCase.getBillNo())
                    .billStatus(BillStatus.ACCEPTED)
                    .billId(FAKE_BILL_ID)
                    .productName(useCase.getProductName())
                    .amount(useCase.getAmount())
                    .seller(SellerModel.builder()
                            .sellerId(FAKE_SELLER_ID)
                            .sellerEmail(useCase.getSellerEmail())
                            .sellerName(useCase.getSellerName())
                            .sellerSurname(useCase.getSellerSurname())
                            .build())
                    .build();
        }else{
            return BillModel.builder()
                    .billNo(useCase.getBillNo())
                    .billStatus(BillStatus.DENIED)
                    .billId(FAKE_BILL_ID)
                    .productName(useCase.getProductName())
                    .amount(useCase.getAmount())
                    .seller(SellerModel.builder()
                            .sellerId(FAKE_SELLER_ID)
                            .sellerEmail(useCase.getSellerEmail())
                            .sellerName(useCase.getSellerName())
                            .sellerSurname(useCase.getSellerSurname())
                            .build())
                    .build();
        }
    }

    @Override
    public List<BillModel> getBillsByStatus(GetBillByStatusUseCase useCase) {
        if(useCase.getBillStatus().equals(BillStatus.ACCEPTED)){
            return List.of(BillModel.builder()
                    .billNo("TR001")
                    .billStatus(BillStatus.ACCEPTED)
                    .billId(1)
                    .productName("Lamp")
                    .amount(20)
                    .seller(SellerModel.builder()
                            .sellerId(3)
                            .sellerEmail("john@doe.com")
                            .sellerName("John")
                            .sellerSurname("Doe")
                            .build())
                    .build(),
                    BillModel.builder()
                            .billNo("TR002")
                            .billStatus(BillStatus.ACCEPTED)
                            .billId(2)
                            .productName("Desk")
                            .amount(70)
                            .seller(SellerModel.builder()
                                    .sellerId(3)
                                    .sellerEmail("john@doe.com")
                                    .sellerName("John")
                                    .sellerSurname("Doe")
                                    .build())
                            .build());
        }else if(useCase.getBillStatus().equals(BillStatus.DENIED)){
            return List.of(BillModel.builder()
                            .billNo("TR003")
                            .billStatus(BillStatus.DENIED)
                            .billId(3)
                            .productName("Book")
                            .amount(20)
                            .seller(SellerModel.builder()
                                    .sellerId(3)
                                    .sellerEmail("john@doe.com")
                                    .sellerName("John")
                                    .sellerSurname("Doe")
                                    .build())
                            .build(),
                    BillModel.builder()
                            .billNo("TR004")
                            .billStatus(BillStatus.DENIED)
                            .billId(4)
                            .productName("Pen")
                            .amount(70)
                            .seller(SellerModel.builder()
                                    .sellerId(3)
                                    .sellerEmail("john@doe.com")
                                    .sellerName("John")
                                    .sellerSurname("Doe")
                                    .build())
                            .build());
        }
        else{
            return List.of();
        }
    }
}
