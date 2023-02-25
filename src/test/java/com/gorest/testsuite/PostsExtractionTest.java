package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts")
                .then().statusCode(200);


    }


    //1. Extract the title
    @Test
    public void Title() {
        List<String> title = response.extract().path("title");
        System.out.println("List of title are:+title");
    }


    //2. Extract the total number of record
    @Test
    public void TotalRecord() {
        int record = response.extract().path("record.size");
        System.out.println("Total number of record is:" + record);
    }


    //3. Extract the body of 15th record
    @Test
    public void body15threcord() {
        String rec15th = response.extract().path("[14].body");
        System.out.println("the body of 15th record is:" + rec15th);
    }


    //4. Extract the user_id of all the records
    @Test
    public void userid() {
        List<Integer> ID = response.extract().path("id");
        System.out.println("the user_id of all the records are:" + ID);
    }


    //5. Extract the title of all the records
    @Test
    public void titleallrecord() {
        List<String> recordtitles = response.extract().path("title");
        System.out.println("the title of all records are:" + recordtitles);
    }


    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void titleuserid() {
        List<?> titleallrecord = response.extract().path("findAll{it.user_id=5456}.title");
    }


    //7. Extract the body of all records whose id = 2671
    @Test
    public void titleofrecords() {
        List<String> titleallbody = response.extract().path("findAll{it.id=2671}.body");
    }
}
