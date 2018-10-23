package com.packt.cardatabase.init;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;

public class DataUtils {

	public static void saveCarsOwners01(CarRepository carRepository, OwnerRepository ownerRepository) {
		ArrayList<Owner> owners = readOwners01();
		for (Owner owner : owners) {
			ownerRepository.save(owner);
		}
		ArrayList<Car> cars = readCars01(owners);
		for (Car car : cars) {
			carRepository.save(car);
		}
	}

	public static void saveUsers01(UserRepository userRepository) {
//		User01
		userRepository.save(new User("user", "$2a$10$lBdKVvzeWCHgdJC9GU0pmes11Szt53nXNBLmpQB3cVK5YB5FaoEam", "USER"));

//		Admin01
		userRepository.save(new User("admin", "$2a$10$er1HL4PHlabj5ilCNnt8XObORwYeGru2wXyZX7zmLoCWq/tlcMtam", "ADMIN"));
	}

	private static ArrayList<Car> readCars01(ArrayList<Owner> owners) {
		ArrayList<Car> cars = new ArrayList<>();
		String fileName = "src\\main\\resources\\savedata\\saveCarsOwners01.txt";
		ArrayList<String> rivit = readStringData(fileName);
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
				if (isInteger(palat[6])) {
					car.setOwner(owners.get(Integer.parseInt(palat[6]) - 1));
				}
				cars.add(car);
			}
		}
		return cars;
	}

	private static ArrayList<Owner> readOwners01() {
		ArrayList<Owner> owners = new ArrayList<>();
		String fileName = "src\\main\\resources\\savedata\\saveOwners01.txt";
		ArrayList<String> rivit = readStringData(fileName);
		for (String rivi : rivit) {
			if (!rivi.substring(0, 1).equals("#")) {
				Owner owner = new Owner();
				String[] palat = rivi.trim().split("\t");
				owner.setFirstname(palat[0]);
				owner.setLastname(palat[1]);
				owners.add(owner);
			}
		}
		return owners;
	}

	private static boolean isInteger(String input) {
		return input.trim().matches("^[+-]?\\d+$");
	}

	private static ArrayList<String> readStringData(String fileName) {
		ArrayList<String> rivit = new ArrayList<>();

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
		cars.add(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, new Owner("John", "Smith")));
		cars.add(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, new Owner("John", "Smith")));
		cars.add(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, new Owner("John", "Smith")));
		cars.add(new Car("Skoda", "Superb", "Purple", "RSV-932", 2011, 10000, new Owner("John", "Smith")));
		return cars;
	}

}
