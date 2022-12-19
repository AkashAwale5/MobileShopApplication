package com.yash.msm.crudrepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.msm.model.Employee;
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

}
