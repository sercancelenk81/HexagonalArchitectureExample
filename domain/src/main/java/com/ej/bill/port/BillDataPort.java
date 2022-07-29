package com.ej.bill.port;

import com.ej.bill.model.BillModel;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.bill.usecase.GetBillByStatusUseCase;


import java.util.List;

public interface BillDataPort {
    BillModel createBill(CreateBillUseCase useCase);

    List<BillModel> getBillsByStatus(GetBillByStatusUseCase useCase);
}
