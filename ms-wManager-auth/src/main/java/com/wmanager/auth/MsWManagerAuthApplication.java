package com.wmanager.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsWManagerAuthApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MsWManagerAuthApplication.class, args);
	}
	
	/*
	 * @Autowired private PasswordEncoder encoder;
	 * 
	 * @Autowired private UserRepository repository;
	 */

	@Override
	public void run(String... args) throws Exception {
		/* System.out.println(encoder.encode("123")); */
		
		/*
		 * User user = new User("admin", "admin@admin.com",
		 * "$2a$10$uBpkZckhtrWa9Fuh8yNrO.eJrauwTNjXREgrW0h66t0jz0g1SXdSC",
		 * UserRole.ADMIN);
		 * 
		 * repository.save(user);
		 */
	}

}
