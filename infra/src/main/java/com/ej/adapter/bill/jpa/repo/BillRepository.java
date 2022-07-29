package com.ej.adapter.bill.jpa.repo;

import com.ej.adapter.bill.jpa.entity.BillEntity;
import com.ej.common.enums.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity,Integer> {
    @Query("SELECT bill FROM BillEntity bill WHERE bill.billNo=:billNo AND bill.billStatus='accepted'")
    BillEntity getBillByBillNo(@Param("billNo")String billNo);
    List<BillEntity> getByBillStatus(BillStatus billStatus);

    @Query("SELECT COALESCE(SUM(amount),0) AS total FROM BillEntity WHERE seller.email=:email AND billStatus='accepted'")
    Integer getSumOfAmount(@Param("email") String email);
}
