package com.cydeo.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class ParametrizedTestsInJunit5  {

    @ParameterizedTest
    @ValueSource(ints = {1,3,4,5,6,7,8,21,32,43,21,23,14,12,4,234,2,12})
    public void testMultipleNumbers(int number){
        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);

    }

    @ParameterizedTest
    @ValueSource(strings = {"Muhtar","Asya","Adam","Ahmet"})
    public void testMultipleNames(String name){
        System.out.println("name = " + name);

    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void test1(int zipCode){

        given()
                .accept(ContentType.JSON)
                .and()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode", zipCode )
        .when()
                .get("/us/{zipcode}")
        .then().log().all()
                .statusCode(200);
    }


}
