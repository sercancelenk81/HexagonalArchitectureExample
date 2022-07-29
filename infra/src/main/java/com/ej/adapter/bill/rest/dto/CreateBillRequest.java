package com.ej.adapter.bill.rest.dto;

import com.ej.bill.usecase.CreateBillUseCase;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBillRequest {
    private String sellerName;

    private String sellerSurname;

    private String sellerEmail;

    private Integer amount;

    private String productName;

    private String billNo;

    public CreateBillUseCase toUseCase() {
        return CreateBillUseCase.builder()
                .productName(productName)
                .sellerName(sellerName)
                .sellerSurname(sellerSurname)
                .sellerEmail(sellerEmail)
                .amount(amount)
                .billNo(billNo).build();
    }
}
