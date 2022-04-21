package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetRequest {

    //beforeall is the same thing with beforeClass in testng
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.201.121.105:8000";
    }


    @DisplayName("GET all spartans")
    @Test
    public void test1() {
 /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8;
    */
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");

      assertEquals(406, response.statusCode());
      assertEquals("application/xml;charset=UTF-8", response.contentType());

    }
}
