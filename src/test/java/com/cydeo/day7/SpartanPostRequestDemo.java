package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanPostRequestDemo extends SpartanTestBase {

    /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @DisplayName("POST a spartan with String json body")
    @Test
    public void test1(){

        String requestBody  ="{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Severus\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";
        Response response = given().accept(ContentType.JSON).log().all() // what we are asking from api which is JSON response
                .and()
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(requestBody)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"),is(expectedMessage));
        assertThat(response.path("data.name"),is("Severus Snepe"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596l));

        response.prettyPrint();
    }

    @DisplayName("POST a spartan with MAP")
    @Test
    public void test2(){

        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("gender","Male");
        requestMap.put("name","Severus Snape");
        requestMap.put("phone",8877445596l);

        Response response = given().accept(ContentType.JSON).log().all() // what we are asking from api which is JSON response
                .and()
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(requestMap)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"),is(expectedMessage));
        assertThat(response.path("data.name"),is("Severus Snape"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596l));

        response.prettyPrint();
    }

    @DisplayName("POST a spartan Spartan class")
    @Test
    public void test3(){

        Spartan spartan = new Spartan();
        spartan.setName("Severus Snape");
        spartan.setGender("Male");
        spartan.setPhone(8877445596l);

        System.out.println(spartan);

        Response response = given().accept(ContentType.JSON).log().all() // what we are asking from api which is JSON response
                .and()
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON request body
                .body(spartan)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"),is(expectedMessage));
        assertThat(response.path("data.name"),is("Severus Snape"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596l));

        response.prettyPrint();
    }
}
