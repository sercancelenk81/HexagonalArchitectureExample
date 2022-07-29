package com.ej.seller.usecase;

import com.ej.common.UseCase;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor
@Getter
public class CreateSellerUseCase implements UseCase {
    private String sellerName;
    private String sellerSurname;
    private String sellerEmail;
}
