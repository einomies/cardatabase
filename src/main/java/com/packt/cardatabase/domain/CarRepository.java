package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

//public interface CarRepository extends CrudRepository<Car, Long> {
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	// Fetch cars by brand
//	List<Car> findByBrand(String brand);

	// Fetch cars by color
	List<Car> findByColor(String color);

	// Fetch cars by year
	List<Car> findByYear(int year);

	/*
	 * There can be multiple fields after the By keyword, concatenated with the And
	 * or Or keywords:
	 */

	// Fetch cars by brand and model
	List<Car> findByBrandAndModel(String brand, String model);

	// Fetch cars by brand or color
	List<Car> findByBrandOrColor(String brand, String color);

	/*
	 * Queries can be sorted by using the OrderBy keyword in the query method:
	 */

	// Fetch cars by brand and sort by year
	List<Car> findByBrandOrderByYearAsc(String brand);

	/*
	 * You can also create queries by using SQL statements, via the @Query
	 * annotation. The following example shows the usage of a SQL query in
	 * CrudRepository.
	 */
	// Fetch cars by brand using SQL
	@Query("select c from Car c where c.brand = ?1")
	List<Car> findByBrand(String brand);

	// Fetch cars by brand (ending string) using SQL
	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrandEndsWith(String brand);

//	näillekin joku Query-annotaatio tms?

	// Returns all entities sorted by the given options
	Iterable<Car> findAll(Sort sort);

	// Returns all entities according to given paging options
	Page<Car> findAll(Pageable pageable);

}
