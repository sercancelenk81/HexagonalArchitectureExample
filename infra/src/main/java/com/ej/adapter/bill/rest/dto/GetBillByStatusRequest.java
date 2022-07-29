package com.ej.adapter.bill.rest.dto;

import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.enums.BillStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBillByStatusRequest {
    private BillStatus billStatus;

    public GetBillByStatusUseCase toUseCase() {
        return GetBillByStatusUseCase.builder()
                .billStatus(billStatus)
                .build();
    }
}
