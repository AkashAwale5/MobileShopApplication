package com.yash.msm.crudrepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.msm.model.Bill;
@Repository
public interface BillRepo extends CrudRepository<Bill, Integer> {

}
