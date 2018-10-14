package com.packt.cardatabase.init;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

public class CarUtils {

//	@Autowired
//	private CarRepository repository;

	public static void saveCars01(CarRepository repository) {
//		ArrayList<Car> cars = readCars01();
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000));
		repository.save(new Car("Skoda", "Superb", "Purple", "RSV-932", 2011, 10000));
	}

	private static ArrayList<Car> readCars01() {
		ArrayList<Car> cars = new ArrayList<>();
		ArrayList<String> rivit = readStringData01();
		for (String rivi : rivit) {
			if (!rivi.substring(0, 0).equals("#")) {
				Car car = new Car();
				String[] palat = rivi.trim().split("\t");
				car.setBrand(palat[0]);
				car.setModel(palat[1]);
				car.setColor(palat[2]);
				car.setRegisterNumber(palat[3]);
				if (palat[4].trim().matches("^[+-]?\\d+$")) {
					car.setYear(Integer.parseInt(palat[4]));
				}
				if (palat[5].trim().matches("^[+-]?\\d+$")) {
					car.setPrice(Integer.parseInt(palat[5]));
				}
			}
		}
		return cars;
	}

	private static ArrayList<String> readStringData01() {
		ArrayList<String> rivit = new ArrayList<>();
		String fileName = "../resources/savedata/saveCars01.txt";

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(rivi -> rivit.add(rivi));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rivit;
	}

}
