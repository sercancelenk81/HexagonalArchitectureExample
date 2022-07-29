package com.ej.bill;

import com.ej.bill.model.BillModel;
import com.ej.bill.port.BillDataPort;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.common.DomainComponent;
import com.ej.common.UseCaseHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainComponent
public class CreateBillUseCaseHandler implements UseCaseHandler<BillModel, CreateBillUseCase> {
    private final BillDataPort billDataPort;
    @Override
    public BillModel handle(CreateBillUseCase useCase) {
        return billDataPort.createBill(useCase);

    }
}
