package com.packt.cardatabase.init;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

public class CarUtils {

//	@Autowired
//	private CarRepository repository;

	public static void saveCars01(CarRepository repository) {
		ArrayList<Car> cars = readCars01();
		for (Car car : cars) {
			repository.save(car);
		}
	}

	private static ArrayList<Car> readCars01() {
		ArrayList<Car> cars = new ArrayList<>();
		ArrayList<String> rivit = readStringData01();
		for (String rivi : rivit) {
			if (!rivi.substring(0, 1).equals("#")) {
				Car car = new Car();
				String[] palat = rivi.trim().split("\t");
				car.setBrand(palat[0]);
				car.setModel(palat[1]);
				car.setColor(palat[2]);
				car.setRegisterNumber(palat[3]);
				if (isInteger(palat[4])) {
					car.setYear(Integer.parseInt(palat[4]));
				}
				if (isInteger(palat[5])) {
					car.setPrice(Integer.parseInt(palat[5]));
				}
				cars.add(car);
			}
		}
		return cars;
	}

	private static boolean isInteger(String input) {
		return input.trim().matches("^[+-]?\\d+$");
	}

	private static ArrayList<String> readStringData01() {
		ArrayList<String> rivit = new ArrayList<>();
		String fileName = "src\\main\\resources\\savedata\\saveCars01.txt";

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(rivi -> rivit.add(rivi));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rivit;
	}

	public static void saveCars02(CarRepository repository) {
		ArrayList<Car> cars = readCars02();
		for (Car car : cars) {
			repository.save(car);
		}
	}

	private static ArrayList<Car> readCars02() {
		ArrayList<Car> cars = new ArrayList<>();
		cars.add(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000));
		cars.add(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000));
		cars.add(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000));
		cars.add(new Car("Skoda", "Superb", "Purple", "RSV-932", 2011, 10000));
		return cars;
	}
}
