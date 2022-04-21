package com.tqs;

import static org.hamcrest.Matchers.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;


public class AppTest 
{
    @Test
    void endpointTest()
    {
        given().when().get("https://jsonplaceholder.typicode.com/todos").then().assertThat().statusCode(200);
    }

    @Test
    void titleOfTodoTest() {
        given().when().get("https://jsonplaceholder.typicode.com/todos/4").then().assertThat()
                .body("title", equalTo("et porro tempora"));
    }

    @Test
    void hasIDsTest() {
        given().when().get("https://jsonplaceholder.typicode.com/todos").then().body("id", hasItems(198, 199));
    }

}
