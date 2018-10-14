package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.init.CarUtils;

@SpringBootApplication
public class CardatabaseApplication {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Spring Boot Application started");
		System.out.println("muutos 01");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Save demo data to database
			com.packt.cardatabase.init.CarUtils.saveCars01(repository);
//			repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000));
//			repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000));
//			repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000));
//			repository.save(new Car("Skoda", "Superb", "Purple", "RSV-932", 2011, 10000));
		};
	}

}
