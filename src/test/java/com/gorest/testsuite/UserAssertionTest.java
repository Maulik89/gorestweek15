package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);

    }

    //  1. Verify the if the total record is 20
    @Test
    public void verifytotal() {
        response.body("size()", equalTo(20));
    }

    @Test
//  2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    public void verifynameid() {
        response.body("[1].name", equalTo("Devvrat Verma"));
    }

    @Test
//  3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    public void checksinglename() {
        response.body("[5].name", equalTo("Lakshminath Deshpande MD"));

    }

    @Test
//  4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    public void checkmultiplenames() {
        response.body("name", hasItems("Fr.Gita Dhawan", "Devrat Verma", "Chatur Mehrotra"));
    }

    @Test
//  5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”

    public void verifyemailid() {
        response.body("[3].email", equalTo("dhawan_chidananda@schiller.com"));

    }

    @Test
//  6. Verify the status is “Active” of user name is “Shanti Bhat V”
    public void verifystatus() {
        response.body("[2].status", equalTo("inactive"));
    }

    @Test
// 7. Verify the Gender = male of user name is “Niro Prajapat”
    public void verifygender() {
        response.body("[2].gender", equalTo("male"));
    }

}
