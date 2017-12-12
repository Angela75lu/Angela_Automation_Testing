package com.ecvictor.api.rest.assured.o2o.twitter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class BitcoinPriceTest {
    private static String host;

    public static Properties getProperties() {
        //load properties from the config file
        String resourceName = "config.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try {
            InputStream resourceStream = loader.getResourceAsStream(resourceName);
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    @BeforeClass
    static public void setUp(){
        Properties prop = getProperties();
        host=prop.getProperty("bitCoinURL");
    }
    @Test
    public void lotto_resource_return_200_with_expected_id_and_winners() {
        HashMap<List<String>, List<String>> map = new HashMap<List<String>, List<String>>();
        List keys = new ArrayList();
        keys.add("BTC");
        keys.add("BTC");
        keys.add("ETC");
        keys.add("ETC");
        List values = new ArrayList();
        values.add("CAD");
        values.add("USD");
        values.add("CAD");
        values.add("USD");
        map.put(keys, values);
        System.out.println(map.keySet().toString());
        System.out.println(map.values().toString());
        System.out.println("map:" + map.toString());
        Iterator iteratorKey = map.keySet().iterator();
        Iterator valueKey = map.values().iterator();
        while (iteratorKey.hasNext()& valueKey.hasNext()) {
            given().log().uri().
                    when().
                    get("https://min-api.cryptocompare.com/data/price?fsym={currency}&tsyms={unit}", iteratorKey.next(), valueKey.next()).
                    then().log().all().
                    statusCode(200).body("CAD", is(not(equalTo(7921.29))));

        }
    }

    @Test
    public void o2oeat_Login_Test_With_Post_Has_Items () {
        given().log().uri().
                when().
                formParam("username","ivy").
                formParam("password", "123456").
                post("http://192.168.88.187:8080/o2oeat/api/login.json").
                then().log().all().
                statusCode(200).
                body("role.name",hasItems("Customer"));

    }

    @Test
    public void o2oeat_Login_Test_With_Post () {
            String response = given().log().uri().
                    when().
                    formParam("username","ivy").
                    formParam("password", "123456").
                    post("http://192.168.88.187:8080/o2oeat/api/login.json").
                    then().log().all().
                    extract().asString();

        JSONObject obj = new JSONObject(response);
        JSONArray roles = obj.getJSONArray("role");

        for(int i=0; i<roles.length(); i++){
            String role_name = roles.getJSONObject(i).getString("name");
        if(role_name.equals("Customer"))
            System.out.println("The Customer role is found ");
        }

    }

    @Test
    public void wrong_symbol_entered(){
        when().
                get("https://min-api.cryptocompare.com/data/price?fsym={currency}&tsyms={unit}","btc","CAD").
                then().log().body().
                statusCode(200).
                body("Response", equalTo("Error")).
                body("Message", equalTo("There is no data for the symbol btc ."));

    }

}



