package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTests {

        /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("One Spartan with Hamcrest and Chaining")
    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
        .when()
                .get("http://44.201.121.105:8000/api/spartans/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and().assertThat()
                .body("id", is(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106));

    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void test2(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10423)
        .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Encoding" ,"gzip")
                .and()
                .header("Date", notNullValue())
                .body("teachers[0].firstName", is("Alexander"),
                        "teachers[0].lastName", is("Syrup"),
                                "teachers[0].gender", is("male"));

    }

    @Test
    public void test3(){

        //verify "Candi", "Alexander", "Francesca" are inside all teachers
        given()
                .accept(ContentType.JSON)
        .when()
                .get("http://api.cybertektraining.com/teacher/all")
        .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName", hasItems("Candi", "Alexander", "Francesca"));

    }
}
