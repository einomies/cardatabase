package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepositoryTemp01 extends PagingAndSortingRepository<Car, Long> {

	List<Car> findByColor(String color);

	List<Car> findByYear(int year);
	
	List<Car> findByBrandAndModel(String brand, String model);
	
	List<Car> findByBrandOrColor(String brand, String color);
	
	List<Car> findByBrandOrderByYearAsc(String brand);

	@Query("select c from Car c where c.brand = ?1")
	List<Car> findByBrand(String brand);

	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrandEndsWith(String brand);

	Iterable<Car> findAll(Sort sort);

	Page<Car> findAll(Pageable pageable);

}
