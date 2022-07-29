package com.ej.adapter.seller.rest;

import com.ej.adapter.seller.rest.dto.CreateSellerRequest;
import com.ej.adapter.seller.rest.dto.CreateSellerResponse;
import com.ej.common.enums.CreateSellerResponseType;
import com.ej.common.UseCaseHandler;
import com.ej.common.result.DataResult;
import com.ej.common.result.Result;
import com.ej.seller.model.SellerModel;
import com.ej.seller.usecase.CreateSellerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final UseCaseHandler<SellerModel, CreateSellerUseCase> useCaseHandler;

    @PostMapping("/create")
    public ResponseEntity<Result> addSeller(@RequestBody CreateSellerRequest createSellerRequest){
        final var createSellerUserCase= createSellerRequest.toUseCase();
        final var sellerModel = useCaseHandler.handle(createSellerUserCase);

        return ResponseEntity.ok().body(new DataResult<>(CreateSellerResponse.from(sellerModel),true, CreateSellerResponseType.CREATE_SELLER_SUCCESS.getMessage()));
    }
}
