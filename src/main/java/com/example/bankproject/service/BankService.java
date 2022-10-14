package com.example.bankproject.service;

import com.example.bankproject.controller.request.*;
import com.example.bankproject.controller.response.DepositResponse;
import com.example.bankproject.model.CashiRepo;
import com.example.bankproject.model.MgniRepo;
import com.example.bankproject.model.entity.Cashi;
import com.example.bankproject.model.entity.Mgni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class BankService {
    @Autowired
    CashiRepo cashiRepo;
    @Autowired
    MgniRepo mgniRepo;

    //Create
    public DepositResponse deposit(DepositRequest request) {
        String id = "MGI" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        setMgni(id, request);
        return new DepositResponse(id, mgniRepo.findById(id));
    }

    private Cashi createCashi(String mgniId, String accNo, String ccy, BigDecimal amt) {
        Cashi cashi = new Cashi();
        {
            cashi.setMgniId(mgniId);
            cashi.setAccNo(accNo);
            cashi.setCcy(ccy);
            cashi.setAmt(amt);
        }
        cashiRepo.save(cashi);
        return cashi;
    }

    //Read
    public List<Mgni> getMgni(MgniRequest request) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (null != request.getId() && !request.getId().isBlank()) {
                    predicates.add(cb.equal(root.get("id"), request.getId()));
                }
                if (null != request.getBankNo() && !request.getBankNo().isBlank()) {
                    predicates.add(cb.equal(root.get("bankNo"), request.getBankNo()));
                }
                if (null != request.getCcy() && !request.getCcy().isBlank()) {
                    predicates.add(cb.equal(root.get("ccy"), request.getCcy()));
                }
                if (null != request.getBicaccNo() && !request.getBicaccNo().isBlank()) {
                    predicates.add(cb.equal(root.get("bicaccNo"), request.getBicaccNo()));
                }
                Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                return predicate;
            }
        };
        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Mgni> mgni = mgniRepo.findAll(specification, pageRequest);
        check(mgni.getContent());
        return mgni.getContent();
    }

    public List<Cashi> getCashi(CashiRequest request) {
        Specification specification = new Specification<Cashi>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (null != request.getMgniId() && !request.getMgniId().isBlank()) {
                    predicates.add(cb.equal(root.get("mgniId"), request.getMgniId()));
                }
                if (null != request.getAccNo() && !request.getAccNo().isBlank()) {
                    predicates.add(cb.equal(root.get("accNo"), request.getAccNo()));
                }
                if (null != request.getCcy() && !request.getCcy().isBlank()) {
                    predicates.add(cb.equal(root.get("ccy"), request.getCcy()));
                }
                query.orderBy(cb.asc(root.get("accNo")));
                Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                return predicate;
            }
        };
        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Cashi> cashi = cashiRepo.findAll(specification, pageRequest);
        check(cashi.getContent());
        return cashi.getContent();
    }

    //Update
    public Mgni updateCashi(UpdateCashiRequest request) {
        Cashi cashi = cashiRepo.findByMgnIdAndAccNoAndCcy(request.getMgniId(), request.getAccNo(), request.getCcy());
        check(cashi);
        cashi.setAmt(request.getAmt());
        cashiRepo.save(cashi);
        setMgni(request.getMgniId());
        return mgniRepo.findById(request.getMgniId());
    }

    public Mgni updateMgni(UpdateMgniRequest request) {
        check(mgniRepo.findById(request.getId()));
        return setMgni(request.getId(), request.getDepositRequest());
    }

    private void setMgni(String id) {
        Mgni mgni = mgniRepo.findById(id);
        check(mgni);
        {
            mgni.setCashiList(cashiRepo.findByMgnId(id));
            BigDecimal totalAmt = new BigDecimal(0);
            for (Cashi cashi : mgni.getCashiList()) {
                totalAmt = totalAmt.add(cashi.getAmt());
            }
            mgni.setAmt(totalAmt);
        }
        if (0 == mgni.getCashiList().size()) {
            mgniRepo.delete(mgni);
        } else {
            mgniRepo.save(mgni);
        }
    }

    @Transactional
    public Mgni setMgni(String id, DepositRequest request) {
        Mgni mgni = new Mgni();
        if (null != mgniRepo.findById(id)) {
            mgni = mgniRepo.findById(id);

        }
        {
            mgni.setId(id);
            mgni.setType("1");
            mgni.setCmNo(request.getCmNo());
            mgni.setKacType(request.getKacType());
            mgni.setBankNo(request.getBankNo());
            mgni.setCcy(request.getCcy());
            mgni.setPvType("1");
            mgni.setBicaccNo(request.getBicaccNo());
            mgni.setCtName(request.getCtName());
            mgni.setCtTel(request.getCtTel());
            mgni.setStatus("0");

            List<String> accList = request.getAcc().stream().map(m -> m.getAccNo()).distinct().collect(Collectors.toList());
            BigDecimal totalAmt = new BigDecimal(0);
            for (String uniAcc : accList) {
                BigDecimal amt = new BigDecimal(0);
                for (Acc acc : request.getAcc()) {
                    if (acc.getAccNo().equals(uniAcc)) {
                        amt = amt.add(acc.getAmt());
                    }
                }
                createCashi(mgni.getId(), uniAcc, request.getCcy(), amt);
                totalAmt = totalAmt.add(amt);
            }
            mgni.setAmt(totalAmt);
        }
        mgniRepo.save(mgni);
        return mgniRepo.findById(id);
    }

    //Delete
    public String deleteCashi(CashiRequest request) {
        Cashi cashi = cashiRepo.findByMgnIdAndAccNoAndCcy(request.getMgniId(), request.getAccNo(), request.getCcy());
        check(cashi);
        cashiRepo.delete(cashi);
        setMgni(request.getMgniId());
        return "Delete succeed";
    }

    public String deleteMgni(IdRequest request) {
        Mgni mgni = mgniRepo.findById(request.getMgniId());
        check(mgni);
        cashiRepo.deleteById(request.getMgniId());
        mgniRepo.deleteById(request.getMgniId());
        return "Delete succeed";
    }

    public void check(Object object) {
        if (null == object) {
            throw new RuntimeException("object null");
        }
    }

    public void check(List list) {
        if (0 == list.size()) {
            throw new RuntimeException("list null");
        }
    }
}
