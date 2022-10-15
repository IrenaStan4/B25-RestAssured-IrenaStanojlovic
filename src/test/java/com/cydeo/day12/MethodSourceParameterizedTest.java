package com.cydeo.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MethodSourceParameterizedTest {


    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name){
        System.out.println("name = " + name);

    }


    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Other APIs
        //Excel file

        List<String> nameList = Arrays.asList("Muhtar","Asya","Gurhan","Adam","Akbar","Aysun","Zulfikar");
        return nameList;
    }

}
