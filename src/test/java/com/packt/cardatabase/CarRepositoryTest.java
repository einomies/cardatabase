package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

/*
 * Instead of the @SpringBootTest annotation, the @DataJpaTest can be used if the
 * test focuses only on JPA components. When using this annotation the H2 database, 
 * Hibernate, and Spring Data are configured automatically for testing. SQL logging 
 * will be also turned on. The tests are transactional by default and roll back at 
 * the end of the test case. TestEntityManager is used to handle the persist entities 
 * and it is designed to be used in testing.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CarRepository repository;

	@Test
	public void saveCar() {
		Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000);
		entityManager.persistAndFlush(car);
		assertThat(car.getId()).isNotNull();
	}

	@Test
	public void deleteCars() {
		entityManager.persistAndFlush(new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000));
		entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow", "BWS-3007", 2015, 24500));
		repository.deleteAll();
		assertThat(repository.findAll()).isEmpty();
	}

}
