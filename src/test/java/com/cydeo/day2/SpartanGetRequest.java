package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String url= "http://54.234.59.235:8000";

    /*
           Given Accept type is application/json
           When user send GET request to  /api/spartans end point
           Then status code must be 200
           And response content type must be application/json
        */
    @Test
    public void test1(){


        Response response = RestAssured.
                given().
                    accept(ContentType.JSON)
                .when()
                    .get(url + "/api/spartans");
        // status code
        System.out.println("response.statusCode() = " + response.statusCode());
        //content type
        System.out.println("response.contentType() = " + response.contentType());
        //how to test api? verify status code
        Assertions.assertEquals(200, response.statusCode());
        //verify content type
        Assertions.assertEquals("application/json", response.contentType());
    }

     /*
        Given accept header is application/json
        When users send a get request to /api/spartans/3
        Then status code must be 200
        And Content type must be application/json
        And json body should contain 'Fidole'
     */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");
        //verify status codee 200
        Assertions.assertEquals(200, response.getStatusCode());

        //verify content type is application/json
        Assertions.assertEquals("application/json", response.getContentType());

        //verify Fidole exist in json body
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }
/*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */
    @DisplayName("GET request to /api/hello endpoint")
    @Test
    public void test3(){
        Response response = RestAssured.when().get(url+"/api/hello");

        response.prettyPrint();
        //verify status code
        Assertions.assertEquals(200, response.statusCode());
        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());
        //verify Date header exists in Response header
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        //to get header value we use header() method which accepts header name as parameter and return value as string
        System.out.println("response.header(\"Content-length\") = " + response.header("Content-length"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));
        //verify content length is 17
        Assertions.assertEquals("17", response.header("Content-length"));

        //verify body is "hello from sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());

    }

}
