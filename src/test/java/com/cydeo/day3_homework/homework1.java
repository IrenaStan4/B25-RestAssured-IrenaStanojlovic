package com.cydeo.day3_homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class homework1 extends HrTestBase {

    @Test
    public void Q1(){

        Response response =
                given().accept(ContentType.JSON).
                        and().pathParam("country_id", "US")
                .when()
                        .get("/countries/{country_id}")
                .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .and()
                        .body("country_id", equalTo("US"))
                        .body("country_name", equalTo("United States of America"))
                        .body("region_id", equalTo(2)).extract().response();

        /*
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", "US")
                .when()
                .get("/countries/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("US", jsonPath.getString("country_id"));

        assertEquals("United States of America", jsonPath.getString("country_name"));

        assertEquals(2, jsonPath.getInt("Region_id"));
         */
    }

    @Test
    public void Q2() {

        Response response =

                given().accept(ContentType.JSON).
                        and().queryParam("q", "{\"department_id\":80}")
                .when()
                        .get("/employees")
                .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .and()
                        .body("items.job_id", everyItem(startsWith("SA")),
                        "items.department_id", everyItem(equalTo(80)),
                                "count", equalTo(25))
                        .extract().response();
        /*
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());


        //response.prettyPrint();

        //all job_ids start with 'SA'
        JsonPath jsonPath = response.jsonPath();

        List<String> startsWithSA = jsonPath.getList("items.job_id");
        System.out.println("startsWithSA = " + startsWithSA);
        for (String s : startsWithSA) {

            assertTrue(s.startsWith("SA"));

        }


        List<Integer> dept_ID = jsonPath.getList("items.department_id");
        System.out.println("dept_ID = " + dept_ID);
        for (Integer each : dept_ID) {

            assertEquals(80, each);

        }
        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(25, count);

    }
         */
    }

    @Test
    public void Q3(){

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\":3}")
                .when().get("/countries")
                .then().statusCode(200)
                .body("items.region_id",everyItem(equalTo(3)),
                        "count",equalTo(6),
                        "hasMore",equalTo(false),
                        "items.country_name", hasItems("Australia", "China", "India", "Japan", "Malaysia", "Singapore")) ;

        /*
        Response response =
            given().accept(ContentType.JSON)
                    .and().queryParam("q","{\"region_id\":3}")
                    .when().get("/countries");
//jsonPath.prettyPrint();
    assertEquals(200, response.statusCode());
    assertEquals("application/json", response.contentType());

    JsonPath jsonPath = response.jsonPath();

    List<Integer> regionID = jsonPath.getList("items.region_id");

        System.out.println("regionID = " + regionID);

        for (Integer eachId : regionID) {
            assertEquals(3,eachId);

        }

        // And count is 6
        //- And hasMore is false
        int count = jsonPath.getInt("count");
        System.out.println("count = " + count);
        assertEquals(6,count);

        boolean hasMore = jsonPath.getBoolean("hasMore");
        System.out.println("hasMore = " + hasMore);
        assertEquals(false,hasMore);


        //- And Country_name are;
        //Australia,China,India,Japan,Malaysia,Singapore

        List<String> expectedCountryNames = new ArrayList<>();
        expectedCountryNames.addAll(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));

        List<String>actualCountryName = jsonPath.getList("items.country_name");

        System.out.println("actualCountryName = " + actualCountryName);
        System.out.println("expectedCountryNames = " + expectedCountryNames);

        assertEquals(expectedCountryNames,actualCountryName);
    }
         */

    }

    /*
          Q1:
        - Given accept type is Json
        - Path param value- US
        - When users send request to /countries
        - Then status code is 200
        - And Content - Type is Json
        - And country_id is US
        - And country_name is United States of America
        - And Region_id is 2

        Q2:
        - Given accept type is Json
        - Query param value - q={"department_id":80}
        - When users sends request to /employees
        - Then status code is 200
        - And Content - Type is Json
        - And all job_ids start with 'SA'
        - And all department_ids are 80
        - Count is 25

        Q3:
        - Given accept type is Json
        -Query param value q = region_id 3
        - When users send request to /countries
        - Then status code is 200
        - And all regions_id is 3
        - And count is 6
        - And hasMore is false
        - And Country_name are;
        Australia,China,India,Japan,Malaysia,Singapore
     */
}
