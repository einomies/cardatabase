package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.OwnerRepository;
import com.packt.cardatabase.domain.UserRepository;

@SpringBootApplication
public class CardatabaseApplication {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Spring Boot CardatabaseApplication started");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			com.packt.cardatabase.init.DataUtils.saveCarsOwners01(carRepository, ownerRepository);
			com.packt.cardatabase.init.DataUtils.saveUsers01(userRepository);
		};
	}

}
