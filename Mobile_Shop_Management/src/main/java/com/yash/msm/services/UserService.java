package com.yash.msm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.msm.crudrepo.UserRepo;
import com.yash.msm.model.MyUserDetails;
import com.yash.msm.model.User;



@Service
public class UserService implements UserDetailsService
{
	@Autowired
	UserRepo objURepo;	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> objuser = objURepo.findByUserName(username);
		 
		 objuser.orElseThrow(()-> new UsernameNotFoundException("Not found :- "+username));
		 return objuser.map(MyUserDetails::new).get();
		
	}


	public void saveUser(User objuser)
	{
		objURepo.save(objuser);		
	}


	public List<User> showAllUser() 
	{
		List<User> ulist= new ArrayList<User>();
		objURepo.findAll().forEach(ulist::add);
		return ulist;
	}
	
   
}
