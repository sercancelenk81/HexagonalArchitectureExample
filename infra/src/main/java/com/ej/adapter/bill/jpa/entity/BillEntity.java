package com.ej.adapter.bill.jpa.entity;

import com.ej.adapter.seller.jpa.entity.SellerEntity;
import com.ej.bill.model.BillModel;
import com.ej.common.enums.BillStatus;
import com.ej.common.model.BillStatusConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bill_id")
    private Integer billId;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private SellerEntity seller;

    @Column(name="amount")
    private Integer amount;

    @Column(name="product_name")
    private String productName;

    @Column(name="bill_no")
    private String billNo;

    @Column(name="bill_status")
    @Convert(converter = BillStatusConverter.class)
    private BillStatus billStatus;

    public BillModel toModel() {
        return BillModel.builder()
                .billId(billId)
                .billNo(billNo)
                .billStatus(billStatus)
                .amount(amount)
                .productName(productName)
                .seller(seller.toModel())
                .build();
    }
}
