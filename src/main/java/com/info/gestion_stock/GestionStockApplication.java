package com.info.gestion_stock;

import com.info.gestion_stock.models.Admin;
import com.info.gestion_stock.services.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStockApplication.class, args);
	}
	@Bean
	CommandLineRunner run(AdminService adminService){
		return args -> {
			adminService.createAdmin(new Admin("admin","0000"));
		};
	}
}
