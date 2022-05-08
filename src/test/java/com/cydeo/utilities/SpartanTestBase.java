package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    //beforeAll is the same thing with beforeClass in testng
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://44.201.121.105:8000";

        String dbUrl="jdbc:oracle:thin:@54.234.59.235:1521:XE";
        String dbUsername="SP";
        String dbPassword="SP";

    //    DBUtils.createConnection(dbUrl, dbUsername,dbPassword);
    }
@AfterAll
    public static void close(){
        DBUtils.destroy();
}

}
