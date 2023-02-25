package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostsAssertionTest {
    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

    //  1. Verify the if the total record is 25
    @Test
    public void test01() {
        response.body("size()", equalTo(25));
    }


    @Test
//  2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
    public void test02() {
        response.body("[19].title", equalTo("Ocer teres voluptatem deripio testimonium agnitio."));
    }

    @Test
//  3. Check the single user_id in the Array list (5522)
    public void singleuserid() {
        response.body("[13].usr_id", equalTo(601039));
    }


    @Test
//  4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    public void multipleids() {
        response.body("[4].id", equalTo(20961));
        response.body("[6].id", equalTo(20958));
        response.body("[10].id", equalTo(20948));
    }


    /* 5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
       spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
        Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
        Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
       antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
        cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
        adflicto. Assentator umquam pel."”
        */
    @Test
    public void bodyuserid() {
        response.body("[20].user_id", equalTo(590495));
    }
}
