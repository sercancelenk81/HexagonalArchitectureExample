package com.ej.bill.usecase;

import com.ej.common.UseCase;
import com.ej.common.enums.BillStatus;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetBillByStatusUseCase implements UseCase {
    private BillStatus billStatus;
}
