package com.ecvictor.api.rest.assured.o2o.Angela_API_Test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class GoogleMapGeocodeTest {
    private static String host;


    @BeforeClass
    static public void setUp() {
        Properties prop = BaseClassConfig.getProperties();
        host=prop.getProperty("googleMapGeocodeURL");
    }

    @Test
    public void GoogleMapGeocodingAddressTest() {
                  given().log().uri().
                    when().
                      get(host+"/json?address=630+Sherbrooke+St+W+#920,+Montreal,+Quebec+H3A+1E4&language=en").
                    then().log().all().
                  //  statusCode(200).body("results.formatted_address",equalTo("630 Rue Sherbrooke Ouest #920, Montréal, QC H3A 1E4, Canada"));
                   statusCode(200).body("results[0].address_components[0].long_name", equalTo("920")).
                          body("results[0].address_components[0].types[0]", equalTo("subpremise"));
    }
    /* test the google map geocoding by setting up the param*/
    @Test
    public void testGoogleMapGeocodingAddressParam() {
        given().log().uri().
                param("address", "630 Sherbrooke St W #920,Montreal,Quebec H3A 1E4").
                param("language", "en").
                when().
                get(host+"/json?/").
                then().log().all().
                  statusCode(200).body("results[0].formatted_address",equalTo("630 Rue Sherbrooke Ouest #920, Montréal, QC H3A 1E4, Canada"));
                //        statusCode(200).body("results[0].address_components[0].long_name", equalTo("920")).
                //body("results[0].address_components[0].types[0]", equalTo("subpremise"));
    }

    /* test the google map geocoding by */
    @Test
    public void testGoogleMapGeocodingAddressParam2() {
        String response = given().log().uri().
                param("address", "630 Sherbrooke St W #920,Montreal,Quebec H3A 1E4").
                param("language", "en").
                when().
                get(host+"/json?/").
                then().log().all().
                extract().asString();

                JSONObject obj = new JSONObject(response);
                String formatted_address = obj.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                if(formatted_address.endsWith("H3A 1E4")){
                    System.out.println("The address was found");
                }
    }

    @Test
    public void testResponseTime() {
        long t = given().get(host+"/json?address=630+Sherbrooke+St+W+#920,+Montreal,+Quebec+H3A+1E4&language=zh-CN").time();
        System.out.println("Time(ms) : " +t);
    }
    @Test
    public void GoogleMapLanguageTest() {
        String response = given().log().uri().
                when().
                get(host+"/json?address=630+Sherbrooke+St+W+#920,+Montreal,+Quebec+H3A+1E4&language=zh-CN").
                then().log().all().
                extract().asString();

        JSONObject obj = new JSONObject(response);
        JSONArray address_comp = obj.getJSONArray("results").getJSONObject(0).getJSONArray("address_components");
        for(int i=0; i<address_comp.length(); i++)
        {
            String temp = address_comp.getJSONObject(i).toString();
            if(temp.contains("加拿大")) {
                System.out.println("The chinese language is choosen");
                break;
            }


        }
                //     statusCode(200).body("results[0].formatted_address",equalTo("630 Rue Sherbrooke Ouest #920, Montréal, QC H3A 1E4, Canada"));
                 //       statusCode(200).body("results[0].address_components", containsString("Montréal"));

    }

    @Test
    public void GoogleMapByLatLngTest() {
        given().log().uri().
                when().
                get(host+"/json?components=country:CA&compoments=route:Rue+Sherbrooke+Ouest&components=postal_code:H3A+1E4").
                then().log().all().
                //     statusCode(200).body("results[0].formatted_address",equalTo("630 Rue Sherbrooke Ouest #920, Montréal, QC H3A 1E4, Canada"));
                        statusCode(200).body("results[0].address_components[0].long_name", equalTo("Canada"));

    }

}

