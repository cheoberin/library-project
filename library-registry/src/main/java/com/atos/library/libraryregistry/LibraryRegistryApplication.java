package com.atos.library.libraryregistry;

import com.atos.library.libraryregistry.model.Role;
import com.atos.library.libraryregistry.model.User;
import com.atos.library.libraryregistry.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@EnableMongoAuditing
@SpringBootApplication
public class LibraryRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryRegistryApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {};
	}

}
