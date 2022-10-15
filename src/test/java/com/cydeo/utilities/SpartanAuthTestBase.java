package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.201.121.105:7000";

        String dbUrl = "jdbc:oracle:thin:@54.145.129.101:1521:";
        String dbUsername = "SP";
        String dbPassword = "SP";

        //DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterAll
    public static void close(){
        //DBUtils.destroy();
    }
}
