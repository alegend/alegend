package com.alegend.www.al.dataModel;

import com.alegend.www.al.activities.ALog;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015-12-17.
 */
public class STestObjectDeserializer implements JsonDeserializer<STestObject> {


    @Override
    public STestObject deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {
        final STestObject stObject = new STestObject();
        final JsonObject jsonObject = json.getAsJsonObject();
        stObject.setInfo(jsonObject.get("info").getAsString());
        stObject.setStatus(jsonObject.get("status").getAsString());

        ALog.i("stGet" + stObject.getInfo());


        return null;
    }
}
