package com.cydeo.day7_homework;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class homework2 extends SpartanAuthTestBase {

    /*
    //homework
    As a homework,write a detailed test for Role Base Access Control(RBAC)
    in Spartan Auth app (7000)
    Admin should be able to take all CRUD
    Editor should be able to take all CRUD
    other than DELETE
    User should be able to only READ data
    not update,delete,create (POST,PUT,PATCH,DELETE)
            --------------------------------------------------------
    Can guest even read data ? 401 for all

     */

    //Admin can read the data
    @DisplayName("GET request as admin")
    @Test
    public void getAdmin(){String admin = "admin";

        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .log().all()
        .when()
                .get("api/spartans")
       .then()
                .statusCode(200).log().all();
    }

    @DisplayName("GET request as editor")
    @Test
    public void getEditor(){

        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON).log().all()
        .when()
                .get("api/spartans")
       .then()
                .statusCode(200).log().all();

        /*
        given().auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .when().get("api/spartans").then().statusCode(200);
         */
    }

    @DisplayName("GET request as user")
    @Test
    public void getUser(){

        given()
                .auth().basic("user", "user")
                .and().accept(ContentType.JSON).log().all()
        .when()
                .get("api/spartans")
        .then()
                .statusCode(200).log().all();

        /*
        given().auth().basic("user", "user").accept(ContentType.JSON)
                .get("/api/spartans").then().statusCode(200);
         */

    }

    @DisplayName("GET request as guest")
    @Test
    public void getGuest(){

        given()
                .auth().basic("guest", "guest")
                .and().accept(ContentType.JSON).log().all()
                .when()
                .get("api/spartans")
                .then()
                .statusCode(401).log().all();
    }

    //Admin can create data
    @DisplayName("POST request as admin")
    @Test
    public void postAdmin(){

        String requestBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Kisela\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response response = given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON).log().all()
                .and().contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(requestBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(), is(201));

        /*
        Spartan spartan = new Spartan();
        spartan.setName("Sina");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);
        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(spartan).when().post("api/spartans")
                .then().statusCode(201);
    }
         */
    }

    @DisplayName("POST request as editor")
    @Test
    public void postEditor(){

        String requestBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Kisela\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response response = given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON).log().all()
                .and().contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(requestBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(), is(201));

        /*
        Spartan spartan = new Spartan();
        spartan.setName("Alper");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);


        given().auth().basic("editor", "editor").accept(ContentType.JSON)
                .and().contentType(ContentType.JSON).body(spartan).when().post("/api/spartans")
                .then().statusCode(201);
         */
    }

    @DisplayName("POST request as user")
    @Test
    public void postUser(){

        String requestBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Kisela\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response response = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON).log().all()
                .and().contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(requestBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(), is(403));

        /*
        Spartan spartan = new Spartan();
        spartan.setName("David");
        spartan.setGender("Male");
        spartan.setPhone(6544567890l);

        //User can not create the data
        given().auth().basic("user", "user").accept(ContentType.JSON).and()
                .contentType(ContentType.JSON).body(spartan).when().post("/api/spartans").then()
                .statusCode(403);
         */
    }

    @DisplayName("POST request as guest")
    @Test
    public void postGuest(){

        String requestBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Kisela\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response response = given()
                .auth().basic("guest", "guest")
                .accept(ContentType.JSON).log().all()
                .and().contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(requestBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(), is(401));
    }

    //Admin can update the data
    @DisplayName("PUT request as admin")
    @Test
    public void putAdmin(){

        Map<String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "AdamSmith");
        putRequest.put("gender", "Male");
        putRequest.put("phone", 8873349999l);

        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 65)
        .when()
                .put("/api/spartans/{id}")
        .then()
                .statusCode(204); //no content

        /*

        Spartan spartan = new Spartan();
        spartan.setName("Sina");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);
        given().auth().basic("admin", "admin")
                .contentType(ContentType.JSON).body(spartan)
                .pathParam("id", 35)
                .when().put("api/spartans/{id}").then()
                .statusCode(204);
    }
         */
    }

    @DisplayName("PUT request as editor")
    @Test
    public void putEditor(){

        Map<String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "AdamSmith");
        putRequest.put("gender", "Male");
        putRequest.put("phone", 8873349999l);

        given()
                .auth().basic("editor", "editor")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 45)
        .when()
                .put("/api/spartans/{id}")
        .then()
                .statusCode(204); //no content

        /*
        Spartan spartan = new Spartan();
        spartan.setName("Alper");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);
        given().auth().basic("editor", "editor")
                .contentType(ContentType.JSON)
                .body(spartan).pathParam("id", 827)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

    }
         */
    }

    @DisplayName("PUT request as user")
    @Test
    public void putUser(){

        Map<String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "AdamSmith");
        putRequest.put("gender", "Male");
        putRequest.put("phone", 8873349999l);

        given()
                .auth().basic("user", "user")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 98)
        .when()
                .put("/api/spartans/{id}")
        .then()
                .statusCode(403); //forbidden

        /*
        Spartan spartan = new Spartan();
        spartan.setName("ABIE");
        spartan.setGender("Male");
        spartan.setPhone(1788387111l);


        given().auth().basic("user", "user")

                .contentType(ContentType.JSON)
                .and().body(spartan).
                pathParam("id", 55).put("/api/spartans/{id}")
                .then().statusCode(403);

         */
    }

    @DisplayName("PUT request as guest")
    @Test
    public void putGuest(){

        Map<String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "AdamSmith");
        putRequest.put("gender", "Male");
        putRequest.put("phone", 8873349999l);

        given()
                .auth().basic("guest", "guest")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 98)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(401); //unauthorized
    }

    @DisplayName("PATCH request as admin")
    @Test
    public void patchAdmin(){

        Map<String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "HelloWorld");

        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 65)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("PATCH request as editor")
    @Test
    public void patchEditor(){

        Map  <String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "HelloWorld");

        given()
                .auth().basic("editor", "editor")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 65)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("PATCH request as user")
    @Test
    public void patchUser(){

        Map  <String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "HelloWorld");

        given()
                .auth().basic("user", "user")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 65)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(403);
    }

    @DisplayName("PATCH request as guest")
    @Test
    public void patchGuest(){

        Map  <String, Object> putRequest = new LinkedHashMap<>();
        putRequest.put("name", "HelloWorld");

        given()
                .auth().basic("guest", "guest")
                .contentType(ContentType.JSON).log().all()
                .and().body(putRequest)
                .and().pathParam("id", 65)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(401);
    }

    //Admin can delete the data
    @DisplayName("DELETE request as admin")
    @Test
    public void deleteAdmin(){

        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON).log().all()
                .and().pathParam("id", 28)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
        /*
        given().auth().basic("admin", "admin")
                .pathParam("id", 98)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

         */
    }

    @DisplayName("DELETE request as editor")
    @Test
    public void deleteEditor(){

        given()
                .auth().basic("editor", "editor")
                .contentType(ContentType.JSON).log().all()
                .and().pathParam("id", 65)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403);
        /*
        given().auth().basic("editor", "editor")
                .contentType(ContentType.JSON)
                .pathParam("id", 827)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(403);
         */
    }

    @DisplayName("DELETE request as user")
    @Test
    public void deleteUser(){

        given()
                .auth().basic("user", "user")
                .contentType(ContentType.JSON).log().all()
                .and().pathParam("id", 65)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403);
        /*
        given().auth().basic("user", "user").contentType(ContentType.JSON)
                    .pathParam("id", 120)
                    .when().delete("/api/spartans/{id}")
                    .then().statusCode(403);
         */
    }

    @DisplayName("DELETE request as guest")
    @Test
    public void deleteGuest(){

        given()
                .auth().basic("guest", "guest")
                .contentType(ContentType.JSON).log().all()
                .and().pathParam("id", 65)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(401);
    }

}
