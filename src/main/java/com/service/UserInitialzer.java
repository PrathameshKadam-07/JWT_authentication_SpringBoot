package com.service;

import com.entity.UserBean;
import com.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitialzer{

	@Bean
	public CommandLineRunner createAdminUser(UserRepo ur,PasswordEncoder pe) {
		return (args -> {
			if(ur.findByUsername("admin").isEmpty()) 
			{
				UserBean admin = new UserBean();
				admin.setUsername("admin");
				admin.setPassword(pe.encode("admin123"));
				admin.setRoles("ROLE_ADMIN");
				
				ur.save(admin);
				System.out.println("Defalut Admin user Created");
			}
		});
	}
}
