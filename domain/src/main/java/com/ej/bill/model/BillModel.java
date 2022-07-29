package com.ej.bill.model;

import com.ej.common.enums.BillStatus;
import com.ej.seller.model.SellerModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BillModel {
    private Integer billId;

    private SellerModel seller;

    private Integer amount;

    private String productName;

    private String billNo;

    private BillStatus billStatus;
}
