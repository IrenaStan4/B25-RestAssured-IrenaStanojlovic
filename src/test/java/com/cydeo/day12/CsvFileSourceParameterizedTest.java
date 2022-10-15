package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CsvFileSourceParameterizedTest {

    // Write a parameterized test for this request
    // Get the data from csv source
    // GET https://api.zippopotam.us/us/{state}/{city}

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void zipCodeTestWithFile(String stateArg, String cityArg, int zipCountArg) {
        System.out.println("state = " + stateArg);
        System.out.println("city = " + cityArg);
        System.out.println("zipCount = " + zipCountArg);

        given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", stateArg)
                .and()
                .pathParam("city", cityArg)
                .when().get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places", hasSize(zipCountArg));

    }
}
