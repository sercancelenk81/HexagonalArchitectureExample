package com.ej.bill;

import com.ej.bill.port.BillFakeDataPort;
import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.enums.BillStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class GetBillByStatusUseCaseHandlerTest {
    GetBillByStatusUseCaseHandler getBillByStatusUseCaseHandler;

    @BeforeEach
    void setUp(){
        getBillByStatusUseCaseHandler = new GetBillByStatusUseCaseHandler(new BillFakeDataPort());
    }

    @Test
    void should_get_accepted_bills(){
        //given
        GetBillByStatusUseCase getBillByStatusUseCase = GetBillByStatusUseCase.builder()
                .billStatus(BillStatus.ACCEPTED)
                .build();

        //when
        var acceptedBills=getBillByStatusUseCaseHandler.handle(getBillByStatusUseCase);

        //then
        Assertions.assertNotNull(acceptedBills);
        Assertions.assertEquals(2,acceptedBills.size());
        Assertions.assertEquals("John",acceptedBills.get(1).getSeller().getSellerName());
        Assertions.assertEquals(BillStatus.ACCEPTED,acceptedBills.get(1).getBillStatus());
    }

    @Test
    void should_get_denied_bills(){
        //given
        GetBillByStatusUseCase getBillByStatusUseCase = GetBillByStatusUseCase.builder()
                .billStatus(BillStatus.DENIED)
                .build();

        //when
        var deniedBills= getBillByStatusUseCaseHandler.handle(getBillByStatusUseCase);

        //
        //then
        Assertions.assertNotNull(deniedBills);
        Assertions.assertEquals(2,deniedBills.size());
        Assertions.assertEquals("John",deniedBills.get(1).getSeller().getSellerName());
        Assertions.assertEquals(BillStatus.DENIED,deniedBills.get(1).getBillStatus());
    }

}
