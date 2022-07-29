package com.ej.adapter.seller.jpa.entity;


import com.ej.seller.model.SellerModel;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "seller")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SellerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "seller_name")
    private String firstName;

    @Column(name = "seller_surname")
    private String lastName;

    @Column(name = "seller_email")
    private String email;

    public SellerModel toModel() {
        return SellerModel.builder()
                .sellerId(sellerId)
                .sellerEmail(email)
                .sellerName(firstName)
                .sellerSurname(lastName)
                .build();
    }
}
