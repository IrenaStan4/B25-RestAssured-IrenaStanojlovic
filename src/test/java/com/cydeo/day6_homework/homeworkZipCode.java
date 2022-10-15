package com.cydeo.day6_homework;

import com.cydeo.pojo.Country;
import com.cydeo.pojo.Places;
import com.cydeo.pojo.Zippo_Country;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class homeworkZipCode {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://api.zippopotam.us";
    }

    /*
    Scenarios:

        Given Accept application/json
        And path zipcode is 22031
        When I send a GET request to /us endpoint
        Then status code must be 200
        And content type must be application/json
        And Server header is cloudflare
        And Report-To header exists
        And body should contain following information
            post code is 22031
            country  is United States
            country abbreviation is US
            place name is Fairfax
            state is Virginia
            latitude is 38.8604
     */

    @DisplayName("GET Zippopotamus hamcrest chaining matcher")
    @Test
    public void test1(){

        given().contentType(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
        .when().get("/us/{zipcode}")
        .then().statusCode(200).log().all()
                .and().contentType("application/json")
                .and().header("Server", "cloudflare")
                .and().header("Report-To", notNullValue())
                .and().body("'post code'", is("22031"))
                .and().body("country", is("United States"))
                .and().body("'country abbreviation'", is("US"))
                .and().body("places[0].'place name'", is("Fairfax"))
                .and().body("places[0].state", is("Virginia"))
                .and().body("places[0].latitude", is("38.8604"));
    }

    @DisplayName("GET Zippopotamus with POJO")
    @Test
    public void test2(){

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().header("Server", "cloudflare")
                .and().header("Report-To", notNullValue())
                .and().extract().response();

        Country country = response.as(Country.class);
        Places places = country.getPlaces().get(0);

        assertThat(country.getPostCode(), is(22031));
        assertThat(country.getCountry(), is("United States"));
        assertThat(country.getCountryAbbreviation(), is("US"));
        assertThat(places.getPlaceName(), equalTo("Fairfax"));
        assertThat(places.getState(), equalTo("Virginia"));
        assertThat(places.getLatitude(), equalTo("38.8604"));

    }

    /*
    Given Accept application/json
        And path zipcode is 50000
        When I send a GET request to /us endpoint
        Then status code must be 404
        And content type must be application/json
     */

    @DisplayName("GET status code 404")
    @Test
    public void test3(){

        given()
                .accept(ContentType.JSON)
                .and().pathParam("zipcode", "50000")
                .when().get("/us/{zipcode}")
                .then().statusCode(404)
                .and().contentType("application/json");
    }

    /*
    Given Accepts application/json
    And path state is va
    And path city is fairfax
    When I send a GET request to /us endpoint
    Then status code must be 200
    And content type must be application/json
    And payload should contain following information
    country abbreviation is US
    country  United States
    place name  Fairfax
    each places must contain fairfax as a value
    each post code must start with 22
     */

    @DisplayName("GET VA and Fairfax hamcrest chaining matcher")
    @Test
    void test4(){

        given()
                .accept(ContentType.JSON)
                .and().pathParam("state", "VA")
                .and().pathParam("city", "fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("'country abbreviation'", is("US"),
                "country", is("United States"),
                "'place name'", is("Fairfax"))
                .and().body("places.'place name'", everyItem(containsString("Fairfax")))
                .and().body("places.'post code'", everyItem(startsWith("22")));
    }

    @DisplayName("GET VA and Fairfax with POJO")
    @Test
    void test5() {

        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("state", "va")
                .and().pathParam("city", "fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().extract().response();

        Zippo_Country zippo_country = response.as(Zippo_Country.class);

        assertThat(zippo_country.getCountryAbbreviation(), is("US"));
        assertThat(zippo_country.getCountry(), equalTo("United States"));
        assertThat(zippo_country.getPlaceName(), equalTo("Fairfax"));
        assertThat(zippo_country.getPlaces(), everyItem(allOf(hasProperty("placeName", containsString("Fairfax")))));
        assertThat(zippo_country.getPlaces(), everyItem(allOf(hasProperty("postCode", startsWith("22")))));

    }
}
