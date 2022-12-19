package com.yash.msm.crudrepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.msm.model.Stock;
@Repository
public interface MobileRepo extends CrudRepository<Stock, Integer>
{
//  @Query(value="select SUM(total_price) from stock")
//  public double totalstock();	
}
