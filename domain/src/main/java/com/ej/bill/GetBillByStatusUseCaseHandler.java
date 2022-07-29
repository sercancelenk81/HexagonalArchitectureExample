package com.ej.bill;

import com.ej.bill.model.BillModel;
import com.ej.bill.port.BillDataPort;
import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.DomainComponent;
import com.ej.common.UseCaseHandler;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@DomainComponent
public class GetBillByStatusUseCaseHandler implements UseCaseHandler<List<BillModel>, GetBillByStatusUseCase> {
    private final BillDataPort billDataPort;

    @Override
    public List<BillModel> handle(GetBillByStatusUseCase useCase) {
        return billDataPort.getBillsByStatus(useCase);
    }
}
