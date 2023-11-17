package com.nju.cloud.repository;


import com.nju.cloud.pojo.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill, String> {

}
