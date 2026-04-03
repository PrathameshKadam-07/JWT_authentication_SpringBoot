package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.UserBean;
import com.repository.UserRepo;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepo userepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBean ub = userepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
			
		return ub;
	}

}
