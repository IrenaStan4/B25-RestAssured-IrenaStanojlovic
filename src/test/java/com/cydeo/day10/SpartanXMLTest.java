package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanXMLTest extends SpartanAuthTestBase {

    //we will ask for xml result
    //assert status code
    //assert content type xml
    //verify first name

    @Test
    public void test1(){

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/xml")
                .body("List.item[0].name",is("Rodolfo"))
                .body("List.item[0].gender",is("Male"))
                .log().all();

    }

    @DisplayName("GET all spartan with XML")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");


        //get response xml body/payload and save inside the xmlpath object
        XmlPath xmlPath = response.xmlPath();

        //get first spartan name
        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        //get me 3rd spartan id number
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        //how to get all names and save into list of String
        List<String> names = xmlPath.getList("List.item.name");
        System.out.println("names = " + names);

    }
}
