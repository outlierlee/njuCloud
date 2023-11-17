package com.nju.cloud.service;

import com.nju.cloud.pojo.Bill;
import com.nju.cloud.repository.BillRepository;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lzm
 * @create: 2023-11-14 23:07
 **/
@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void saveEntity(Bill bill) {
        billRepository.save(bill);
    }


}
