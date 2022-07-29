package com.ej.adapter.bill.rest.dto;

import com.ej.adapter.seller.jpa.entity.SellerEntity;
import com.ej.bill.model.BillModel;
import com.ej.common.enums.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor

public class GetBillByStatusResponse {

    private Integer billId;

    private SellerEntity seller;

    private Integer amount;

    private String productName;

    private String billNo;

    private BillStatus billStatus;

    public static GetBillByStatusResponse from(BillModel billModel) {
        final var sellerEntity= SellerEntity.builder()
                .sellerId(billModel.getSeller().getSellerId())
                .firstName(billModel.getSeller().getSellerName())
                .lastName(billModel.getSeller().getSellerSurname())
                .email(billModel.getSeller().getSellerEmail())
                .build();

        return GetBillByStatusResponse.builder()
                .billId(billModel.getBillId())
                .billNo(billModel.getBillNo())
                .amount(billModel.getAmount())
                .seller(sellerEntity)
                .billStatus(billModel.getBillStatus())
                .productName(billModel.getProductName())
                .build();
    }

}
