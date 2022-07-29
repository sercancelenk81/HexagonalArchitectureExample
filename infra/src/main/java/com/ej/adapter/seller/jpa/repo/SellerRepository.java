package com.ej.adapter.seller.jpa.repo;

import com.ej.adapter.seller.jpa.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerEntity,Integer> {
    SellerEntity getSellerByEmail(String email);
}
