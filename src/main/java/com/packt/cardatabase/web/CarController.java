package com.packt.cardatabase.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;

@RestController
public class CarController {

	@Autowired
	private CarRepository carRepository;
	
	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return carRepository.findAll();
	}
/*
	@RequestMapping("/cars-by-brand")
	public Iterable<Car> getCars(String brand) {
		return carRepository.findByBrand(brand);
	}
*/
	@RequestMapping("/dummy")
	public Owner getDummyOwner() {
		Owner owner = new Owner("David", "Dummy");
		owner.setOwnerid(999);
		List<Car> cars = new ArrayList<>();
		owner.setCars(cars);
		return owner;
	}

}
