package com.ecvictor.api.rest.assured.o2o.ecvicms;

/**
 * Created by caoc on 3/24/2017.
 */


import com.ecvictor.api.rest.assured.o2o.RestAssuredUtil;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.Properties;
import java.util.Random;

import static com.ecvictor.api.rest.assured.o2o.RestAssuredUtil.getProperties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by caoc on 2/22/16.
 * Copyright (c) 2015 Service ECVictor Inc. All rights reserved.
 */
public class WPDemoTest {

    @BeforeClass
    public static void setup() {
        RestAssuredUtil.setup();
    }


    @Test
    public void testGetPosts() {
         Random r = new Random();
         int low = 0;
         int high = 9;
         int index = r.nextInt(high-low) +low;
        int id =
                given()
                        .when()
                        .get("/wp-json/wp/v2/posts").
                        then().
                        extract().
                        path("["+index+"].id");
        given()
                .when()
                .get("/wp-json/wp/v2/posts/" +id).
                then().
                body("status", equalTo("publish"));


    }

    @Test
    public void testGetPostNotExsit() {
        given()
                .when()
                .get("/wp-json/wp/v2/posts/2")
                .then()
                .statusCode(404)
                .body("message", equalTo("Invalid post ID."));
    }


}