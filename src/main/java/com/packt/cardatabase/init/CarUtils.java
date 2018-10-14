package com.packt.cardatabase.init;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

public class CarUtils {
	
//	@Autowired
//	private CarRepository repository;
	
	public static void saveCars01(CarRepository repository) {
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000));
		repository.save(new Car("Skoda", "Superb", "Purple", "RSV-932", 2011, 10000));
	}

}
