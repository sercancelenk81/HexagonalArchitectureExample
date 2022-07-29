package com.ej.bill.usecase;

import com.ej.common.enums.BillStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetBillByStatusUseCaseTest {
    @Test
    void should_create(){
        //given
        BillStatus billStatus = BillStatus.ACCEPTED;

        //when
        final var getBillByStatusUseCase = new GetBillByStatusUseCase(billStatus);

        //then
        Assertions.assertEquals(getBillByStatusUseCase.getBillStatus(),billStatus);
    }
}
