package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;


    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "2")
                .get("/users")
                .then().statusCode(200);
    }

    @Test
//  1. Extract the All Ids
    public void extrctalllid() {
        List<String> Allids = response.extract().path("id");

    }

    @Test
//  2. Extract the all Names
    public void extractallnames() {
        List<String> Allnames = response.extract().path(("name"));
        System.out.println("List of names are");
    }

    @Test
//  3. Extract the name of 5th object
    public void name5thobject() {
        String name = response.extract().path("data[4].name");
        System.out.println("Subhaprada Pothuvaal Jr");
    }


    @Test

//  4. Extract the names of all object whose status = inactive
    public void nameallobject() {
        List<Map<String, ?>> inactivestatus = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("name of inactive:" + inactivestatus);

    }

    @Test
//  5. Extract the gender of all the object whose status = active
    public void extractgender() {
        List<Map<String, ?>> activestatus = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println();
    }

    @Test
//  6. Print the names of the object whose gender = female
    public void printname() {
        List<Map<String, ?>> names = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("Name of fenale");
    }

    @Test
//  7. Get all the emails of the object where status = inactive
    public void allemails() {
        List<Map<String, ?>> emails = response.extract().path("findAll{it.status=='inactive}.email");
        System.out.println("name of female:" + emails);

    }

    @Test
//  8. Get the ids of the object where gender = male
    public void getids() {
        List<String> ids = response.extract().path("findAll{it,gender=='male'");
        System.out.println("list of ids:" + ids);
    }

    @Test
//  9. Get all the status
    public void allstatus() {
        List<String> allstatus = response.extract().path("status");
        System.out.println("list of status are:" + allstatus);
    }

    @Test
//  10. Get email of the object where name = Karthik Dubashi IV
    public void email() {
        String email = response.extract().path("data[13].email");
        System.out.println("pillai_devesh@ratke.io");
    }

    @Test
//  11. Get gender of id = 5471
    public void genderid() {
        String gender = response.extract().path("data[9].gendr");
        System.out.println("male");
    }
}
