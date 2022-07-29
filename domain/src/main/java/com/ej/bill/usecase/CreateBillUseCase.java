package com.ej.bill.usecase;

import com.ej.common.UseCase;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateBillUseCase implements UseCase {
    private String sellerName;

    private String sellerSurname;

    private String sellerEmail;

    private Integer amount;

    private String productName;

    private String billNo;
}
