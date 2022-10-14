package com.example.bankproject.model;

import com.example.bankproject.model.entity.Cashi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CashiRepo extends JpaRepository<Cashi, Integer> , JpaSpecificationExecutor<Cashi> {

    @Query(value = "SELECT * FROM CASHI where CASHI_MGNI_ID = ?1 and CASHI_ACC_NO = ?2 and CASHI_CCY = ?3 ;", nativeQuery = true)
    Cashi findByMgnIdAndAccNoAndCcy(String mgniId,String accNo,String ccy);

    @Query(value = "SELECT * FROM CASHI where CASHI_MGNI_ID = ?1 ;", nativeQuery = true)
    List<Cashi> findByMgnId(String mgniId);


    @Transactional
    @Modifying
    @Query(value = "delete FROM CASHI where CASHI_MGNI_ID = ?1 ;",nativeQuery = true)
    void deleteById(String id);
}
