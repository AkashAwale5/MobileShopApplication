package com.yash.msm.crudrepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.msm.model.Offers;
@Repository
public interface OffersRepo extends CrudRepository<Offers, Integer> {

}
