package com.cydeo.day5;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HRHamcrestTest extends HrTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void test1() {

        //TASK
        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify status code and content type
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)

        //expected names
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.salary",everyItem(greaterThan(3000)))
                .body("items.first_name",equalTo(names))
                .body("items.email",containsInAnyOrder("DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"));

    }


    @Test
    public void test2(){
        //we want to chain with hamcrest and also get the response object.
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .extract().response().jsonPath();

        //extract() --> method that allow us to get response object after we use then() method.

        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.firstname"),hasSize(5));



    }
}