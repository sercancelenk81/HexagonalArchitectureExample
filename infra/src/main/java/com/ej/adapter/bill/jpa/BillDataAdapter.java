package com.ej.adapter.bill.jpa;

import com.ej.adapter.bill.jpa.entity.BillEntity;
import com.ej.adapter.bill.jpa.repo.BillRepository;
import com.ej.adapter.seller.jpa.entity.SellerEntity;
import com.ej.adapter.seller.jpa.repo.SellerRepository;
import com.ej.bill.model.BillModel;
import com.ej.bill.port.BillDataPort;
import com.ej.bill.usecase.CreateBillUseCase;
import com.ej.bill.usecase.GetBillByStatusUseCase;
import com.ej.common.enums.BillStatus;
import com.ej.common.exception.BillNoAlreadyExistException;
import com.ej.common.exception.SellerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillDataAdapter implements BillDataPort {

    private final BillRepository billRepository;
    private final SellerRepository sellerRepository;

    @Value("${maxLimit}")
    public Integer amountLimit;

    @Override
    public BillModel createBill(CreateBillUseCase useCase){

        if(Objects.nonNull(billRepository.getBillByBillNo(useCase.getBillNo()))){
            throw new BillNoAlreadyExistException(useCase.getBillNo());
        }

        SellerEntity sellerEntity=sellerRepository.getSellerByEmail(useCase.getSellerEmail());

        if(Objects.isNull(sellerEntity)){
            throw new SellerNotFoundException(useCase.getSellerEmail());
        }

        final var billEntity=BillEntity.builder()
                .seller(sellerEntity)
                .billNo(useCase.getBillNo())
                .amount(useCase.getAmount())
                .productName(useCase.getProductName())
                .build();



        if(!isOverLimit(billEntity)){
            billEntity.setBillStatus(BillStatus.ACCEPTED);
            return billRepository.save(billEntity).toModel();
        }

        billEntity.setBillStatus(BillStatus.DENIED);
        return billRepository.save(billEntity).toModel();

    }

    @Override
    public List<BillModel> getBillsByStatus(GetBillByStatusUseCase getBillByStatusUseCase) {
        return billRepository.getByBillStatus(getBillByStatusUseCase.getBillStatus())
                .stream().map(BillEntity::toModel).collect(Collectors.toList());

    }

    private boolean isOverLimit(BillEntity bill){
        return billRepository.getSumOfAmount(bill.getSeller().getEmail()) + bill.getAmount() > amountLimit;
    }

}
