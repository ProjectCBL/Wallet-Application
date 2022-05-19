package com.cg.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cg.wallet.controller.WalletAppController;
import com.cg.wallet.service.ApplicationService;
import com.cg.wallet.service.ApplicationServiceImpl;

@CrossOrigin
@SpringBootApplication
@EnableJpaRepositories("com.cg.wallet.repository")
@EntityScan("com.cg.wallet.entities") 
@ComponentScan(basePackageClasses = WalletAppController.class)
public class WalletApplicationSpringApplication {

	@Bean
	public ApplicationService sessionService() {
	   return new ApplicationServiceImpl();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WalletApplicationSpringApplication.class, args);
	}

}
