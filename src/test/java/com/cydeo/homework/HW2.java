package com.cydeo.homework;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HW2 {


    @DisplayName("GET all spartans from /api/spartans as admin")
    @Test
    public void test1(){
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

        given().auth().basic("admin", "admin").accept(ContentType.JSON).when()
                .get("/api/spartans")
                .then().statusCode(200).contentType("application/json").log().all();
    }

    @DisplayName("POST spartan to /api/spartans as admin, users expects 201")
    @Test
    public void test2(){

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("gender", "Female");
        map.put("name", "Nelly");
        map.put("phone", 99941282355l);

        given().auth().basic("admin", "admin").accept(ContentType.JSON).and().contentType(ContentType.JSON).body(map)
                .when()
                .post("/api/spartans")
                .then().statusCode(201).log().all();
    }

    @DisplayName("PUT spartan to /api/spartans as admin")
    @Test
    public void test3(){

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("gender", "Male");
        map.put("name", "Mihajlo");
        map.put("phone", 99941282366l);

        given().auth().basic("admin", "admin").accept(ContentType.JSON).and().contentType(ContentType.JSON).body(map).log().all()
                .and().pathParam("id", 104)
                .when()
                .put("/api/spartans/{id}")
                .then().statusCode(204).log().all();

    }

    @DisplayName("PATCH spartan to /api/spartans as admin")
    @Test
    public void test4(){

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phone", 99941276576l);

        given().auth().basic("admin", "admin").accept(ContentType.JSON).and().contentType(ContentType.JSON).body(map).log().all()
                .and().pathParam("id", 104)
                .when()
                .patch("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }
    @DisplayName("DELETE spartan to /api/spartans as admin")
    @Test
    public void test5(){

        given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().pathParam("id", 104)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(404).log().all();
    }

}
