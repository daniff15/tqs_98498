package com.tqs.lab7_3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class Lab73ApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Container
  	public static PostgreSQLContainer container = new PostgreSQLContainer()
  	  .withUsername("duke")
  	  .withPassword("password")
  	  .withDatabaseName("test");

  	@DynamicPropertySource
  	static void properties(DynamicPropertyRegistry registry) {
  	  registry.add("spring.datasource.url", container::getJdbcUrl);
  	  registry.add("spring.datasource.password", container::getPassword);
  	  registry.add("spring.datasource.username", container::getUsername);
  	}

	@Test
	@Order(1)
	void contextLoads() {
		Book book = new Book();
    	book.setName("Testcontainers");
    	bookRepository.save(book);

    	System.out.println("Context loads!");
	}

}
