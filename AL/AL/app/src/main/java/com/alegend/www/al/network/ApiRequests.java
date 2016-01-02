package com.alegend.www.al.network;

import android.support.annotation.NonNull;

import com.alegend.www.al.BuildConfig;
import com.alegend.www.al.activities.ALog;
import com.alegend.www.al.dataModel.DummyObject;
import com.alegend.www.al.dataModel.DummyObjectDeserializer;
import com.alegend.www.al.dataModel.STestObject;
import com.alegend.www.al.dataModel.STestObjectDeserializer;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;

/**
 * Api requests
 */
public class ApiRequests
{
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(DummyObject.class, new DummyObjectDeserializer())
            .create();

    /**
     * Returns a dummy object
     *
     * @param listener is the listener for the correct answer
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonGetRequest}
     */
    public static GsonGetRequest<DummyObject> getDummyObject
    (

            @NonNull final Response.Listener<DummyObject> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url ="\"http://www.mocky.io\""+ "/v2/55973508b0e9e4a71a02f05f";
        ALog.i("url" + url);
        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<DummyObject>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }

    /**
     * Returns a dummy object's array
     *
     * @param listener is the listener for the correct answer
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonGetRequest}
     */
    public static GsonGetRequest<ArrayList<DummyObject>> getDummyObjectArray
    (
            @NonNull final Response.Listener<ArrayList<DummyObject>> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = "http://www.mocky.io" + "/v2/5597d86a6344715505576725";

        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<ArrayList<DummyObject>>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }


    /**
     * An example call (not used in this app example) to demonstrate how to do a Volley POST call
     * and parse the response with Gson.
     *
     * @param listener is the listener for the success response
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonPostRequest}
     */
    public static GsonPostRequest getDummyObjectArrayWithPost
    (
            @NonNull final Response.Listener<DummyObject> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {

        final String url = "http://www.mocky.io" + "/dummyPath";

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Ficus");
        jsonObject.addProperty("surname", "Kirkpatrick");

        final JsonArray squareGuys = new JsonArray();
        final JsonObject dev1 = new JsonObject();
        final JsonObject dev2 = new JsonObject();
        dev1.addProperty("name", "Jake Wharton");
        dev2.addProperty("name", "Jesse Wilson");
        squareGuys.add(dev1);
        squareGuys.add(dev2);

        jsonObject.add("squareGuys", squareGuys);

        final GsonPostRequest gsonPostRequest = new GsonPostRequest<>
                (
                        url,
                        jsonObject.toString(),
                        new TypeToken<DummyObject>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );

        gsonPostRequest.setShouldCache(false);

        return gsonPostRequest;
    }

    /**
     *
     * @param listener
     * @param errorListener
     * @return
     */
    public static  GsonPostRequest getTest(
            @NonNull final Response.Listener<STestObject> listener,
            @NonNull final Response.ErrorListener errorListener

    ){
        gson = new GsonBuilder().registerTypeAdapter(STestObject.class,new STestObjectDeserializer()).create();
        final String url ="http://121.42.167.213/index.php?s=/Jmobile/GoodsCarV2/addGoods/json/";

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("matches", "1");
        jsonObject.addProperty("otherid", "216");
        jsonObject.addProperty("user_id", "370");
        jsonObject.addProperty("advance", "240");
        jsonObject.addProperty("type", "2");
        jsonObject.addProperty("money", "800");
        jsonObject.addProperty("num", "1");


//        final JsonArray squareGuys = new JsonArray();
//        final JsonObject dev1 = new JsonObject();
//        final JsonObject dev2 = new JsonObject();
//        dev1.addProperty("name", "Jake Wharton");
//        dev2.addProperty("name", "Jesse Wilson");
//        squareGuys.add(dev1);
//        squareGuys.add(dev2);
//
//        jsonObject.add("squareGuys", squareGuys);

        final GsonPostRequest gsonPostRequest = new GsonPostRequest<>
                (
                        url,
                        jsonObject.toString(),
                        new TypeToken<STestObject>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );

        gsonPostRequest.setShouldCache(false);

        return gsonPostRequest;
    }
}
