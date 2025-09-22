package com.car_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.car_rental"})
@EnableJpaRepositories(basePackages = {"com.car_rental.repo"})
@EnableMongoRepositories(basePackages = {"com.car_rental.repo.mongo"})
@EntityScan(basePackages = {"com.car_rental.entity"})
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

}
