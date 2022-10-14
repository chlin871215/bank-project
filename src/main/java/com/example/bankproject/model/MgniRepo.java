package com.example.bankproject.model;

import com.example.bankproject.model.entity.Cashi;
import com.example.bankproject.model.entity.Mgni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MgniRepo extends JpaRepository<Mgni, Integer> , JpaSpecificationExecutor<Cashi> {

    @Query(value = "SELECT * FROM MGNI where MGNI_ID = ?1  ;", nativeQuery = true)
    Mgni findById(String id);

    @Transactional
    @Modifying
    @Query(value = "delete FROM MGNI where MGNI_ID = ?1  ;", nativeQuery = true)
    void deleteById(String id);

}
