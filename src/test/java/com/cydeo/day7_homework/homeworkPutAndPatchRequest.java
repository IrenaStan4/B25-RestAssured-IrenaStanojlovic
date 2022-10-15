package com.cydeo.day7_homework;

import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class homeworkPutAndPatchRequest {

    /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
       Can guest even read data ? 401 for all

     */

    @DisplayName("Admin should be able to take al CRUD")
    @Test
    public void test1() {

        //map deserialize
        Map<String, Object> spartan = new HashMap<>();
        spartan.put("name", "Smith");
        spartan.put("gender", "Male");
        spartan.put("phone", 1326547777L);

        int id = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic("admin", "admin")
                .body(spartan)
                .post("/api/spartans").then().statusCode(201)
                .contentType("application/json")
                .body("data.name", is("Smith"), "data.gender", is("Male"), "data.phone", is(1326547777L))
                .extract().jsonPath().getInt("data.id");


        //deserialize -> GET

        Spartan spartan1 = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200).contentType("application/json").extract().jsonPath().getObject("", Spartan.class);

        //creating spartan with the same name again
        //serialize -> POST
        int newID = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic("admin", "admin")
                .body(spartan1)
                .log().all()
                .post("/api/spartans").then().statusCode(201).contentType("application/json")
                .body("data.name", is("Smith"), "data.gender", is("Male"), "data.phone", is(1326547777L))
                .extract().jsonPath().getInt("data.id");


        //PUT
        spartan1.setName("Smith");

        given().contentType(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", newID)
                .body(spartan1).log().all()
                .put("/api/spartans/{id}")
                .then().statusCode(204);

        //verify the put request
        given().accept(ContentType.JSON)
                .pathParam("id", newID)
                .auth().basic("admin", "admin")
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).contentType("application/json")
                .body("name", is(spartan1.getName()));


        //PATCH
        spartan.put("name", "William");

        given().contentType(ContentType.JSON)
                .pathParam("id", newID)
                .log().all()
                .auth().basic("admin", "admin")
                .body(spartan)
                .patch("/api/spartans/{id}")
                .then().statusCode(204);


        //verify patch
        given().accept(ContentType.JSON)
                .pathParam("id", newID)
                .auth().basic("admin", "admin")
                .get("/api/spartans/{id}").then()
                .statusCode(200).contentType("application/json")
                .body("name", is("William"));


        //DELETE
        given().pathParam("id", newID)
                .auth().basic("admin", "admin")
                .delete("/api/spartans/{id}")
                .then().statusCode(204);


        //verify delete
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", newID)
                .get("/api/spartans/{id}").then()
                .statusCode(404);
    }

    @DisplayName("Some Guest actions")
    @Test
    public void test2() {

        //GET

        given().accept(ContentType.JSON)
                .get("/api/spartans")
                .then().statusCode(401);

        //PUT
        given().contentType(ContentType.JSON)
                .pathParam("id", 120)
                .body("\"id\": 110,\n" +
                        "    \"name\": \"Dan\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 1326547777")
                .put("/api/spartans/{id}")
                .then().statusCode(401);


    }


    @DisplayName("User should be able to only READ data")
    @Test
    public void test3() {

        //get
        given().accept(ContentType.JSON)
                .auth().basic("user", "user")
                .get("/api/spartans")
                .then().statusCode(200).contentType(ContentType.JSON);

        //patch
        given().contentType(ContentType.JSON)
                .auth().basic("user", "user")
                .pathParam("id", 110)
                .body(" \"name\": \"Dan\"")
                .patch("/api/spartans/{id}")
                .then().statusCode(403);


    }
}
