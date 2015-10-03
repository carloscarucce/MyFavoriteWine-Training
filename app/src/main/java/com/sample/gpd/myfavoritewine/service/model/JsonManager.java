package com.sample.gpd.myfavoritewine.service.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kauesantoja on 03/10/2015.
 */
public abstract class JsonManager {

    protected String getString(String field,JSONObject jsonObject){
        String result = null;
        try {
            if (jsonObject.has(field)) {
                result = jsonObject.getString(field);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return result;
    }

}
