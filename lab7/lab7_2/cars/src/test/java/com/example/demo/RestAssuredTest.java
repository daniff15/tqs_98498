package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import com.example.demo.JsonUtils;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarRestController.class)
class RestAssuredTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CarManagerService carManagerService;

	@BeforeEach
	public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mvc);
	}

    @Test
	public void whenPostCar_thenReturnCar() throws Exception {
		Car car = new Car("Nissan", "GT-R");

		when(carManagerService.save(Mockito.any())).thenReturn(car);


        RestAssuredMockMvc.given().header("Content-Type", "application/json").body(JsonUtils.toJson(car))
                .post("/api/newcar")
                .then().assertThat().statusCode(201)
                .and().body("maker", equalTo("Nissan"))
                .and().body("model", equalTo("GT-R"));
        
        verify(carManagerService, times(1)).save(car);
	}

    @Test 
    public void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {

        Car car1 = new Car("Hummer", "H2 533CV");
		Car car2 = new Car("Rolls-Royce", "Boat Tail");
		Car car3 = new Car("Bugatti", "Centodieci");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

		when(carManagerService.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given().header("Content-Type", "application/json").get("api/cars").then().assertThat().statusCode(200)
        .and().body("", hasSize(3))
        .and().body("maker[0]", is(car1.getMaker()))
        .and().body("model[0]", is(car1.getModel()))
        .and().body("maker[1]", is(car2.getMaker()))
        .and().body("model[1]", is(car2.getModel()))
        .and().body("maker[2]", is(car3.getMaker()))
        .and().body("model[2]", is(car3.getModel()));

        verify(carManagerService, times(1)).getAllCars();

    }

}
