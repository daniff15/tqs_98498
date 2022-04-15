package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarRestController.class)
class CarControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CarManagerService carManagerService;

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	void whenPostCar_thenReturnCar() throws Exception {
		Car car = new Car("Nissan", "GT-R");

		when(carManagerService.save(Mockito.any())).thenReturn(car);

		mvc.perform(
				post("/api/newcar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.maker", is("Nissan")))
				.andExpect(jsonPath("$.model", is("GT-R")));

		verify(carManagerService, times(1)).save(Mockito.any());

	}

	@Test
	void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
		Car car1 = new Car("Hummer", "H2 533CV");
		Car car2 = new Car("Rolls-Royce", "Boat Tail");
		Car car3 = new Car("Bugatti", "Centodieci");

		List<Car> allCars = Arrays.asList(car1, car2, car3);

		when(carManagerService.getAllCars()).thenReturn(allCars);

		mvc.perform(
				get("/api/cars").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
				.andExpect(jsonPath("$[0].model", is(car1.getModel())))
				.andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
				.andExpect(jsonPath("$[1].model", is(car2.getModel())))
				.andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
				.andExpect(jsonPath("$[2].model", is(car3.getModel())));
		verify(carManagerService, times(1)).getAllCars();

	}

	@Test
	void whenPostCarDTO_thenReturnCarByID() throws Exception {
		CarDTO cardto = new CarDTO("Hummer", "H2 533CV");
		Car car1 = new Car();
		car1.setMaker(cardto.getMaker());
		car1.setModel(cardto.getModel());
		car1.setCarID(500L);

		when(carManagerService.getCarDetails(car1.getCarID())).thenReturn(car1);

		String url = "/api/car/" + car1.getCarID(); 
		mvc.perform(
			get(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("maker", is("Hummer")))
			.andExpect(jsonPath("model", is("H2 533CV")));
		verify(carManagerService, times(1)).getCarDetails(car1.getCarID());

	}

}
