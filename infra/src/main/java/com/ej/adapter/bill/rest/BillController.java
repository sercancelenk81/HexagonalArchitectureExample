package com.ej.adapter.bill.rest;

import com.ej.adapter.bill.rest.dto.CreateBillRequest;
import com.ej.adapter.bill.rest.dto.CreateBillResponse;
import com.ej.adapter.bill.rest.dto.GetBillByStatusRequest;
import com.ej.adapter.bill.rest.dto.GetBillByStatusResponse;
import com.ej.common.enums.BillStatus;
import com.ej.common.enums.CreateBillResponseType;
import com.ej.bill.model.BillModel;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.UseCaseHandler;
import com.ej.common.result.DataResult;
import com.ej.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final UseCaseHandler<BillModel, CreateBillUseCase> useCaseHandler;
    private final UseCaseHandler<List<BillModel>, GetBillByStatusUseCase> getBillByStatusUseCaseUseCaseHandler;

    @PostMapping("/create")
    public ResponseEntity<Result> addBill(@RequestBody CreateBillRequest createBillRequest) {
        final var createBillUseCase = createBillRequest.toUseCase();
        final var billModel = useCaseHandler.handle(createBillUseCase);

        if (BillStatus.ACCEPTED.equals(billModel.getBillStatus())) {
            return ResponseEntity.ok()
                    .body(new DataResult<>(CreateBillResponse.from(billModel), true, CreateBillResponseType.CREATE_BILL_SUCCESS.getMessage()));
        }

        return ResponseEntity.badRequest()
                .body(new DataResult<>(CreateBillResponse.from(billModel), false, CreateBillResponseType.CREATE_BILL_FAILURE.getMessage()));

    }

    @GetMapping("/get-bills/{status}")
    public ResponseEntity<List<GetBillByStatusResponse>> getBillsByStatus(@PathVariable String status) {
        BillStatus billStatus = BillStatus.valueOf(status.toUpperCase());
        GetBillByStatusRequest statusRequest = GetBillByStatusRequest.builder().billStatus(billStatus).build();

        final var getBillByStatusUseCase = statusRequest.toUseCase();
        final var bills = getBillByStatusUseCaseUseCaseHandler.handle(getBillByStatusUseCase)
                .stream().map(GetBillByStatusResponse::from).collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(bills);
    }


}
