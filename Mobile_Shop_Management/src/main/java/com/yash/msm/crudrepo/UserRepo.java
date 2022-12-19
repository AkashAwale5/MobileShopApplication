package com.yash.msm.crudrepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.msm.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
	Optional<User> findByUserName(String userName);
}
