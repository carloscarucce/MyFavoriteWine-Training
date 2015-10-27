package com.sample.gpd.myfavoritewine.service.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kauesantoja on 03/10/2015.
 */
public class User extends JsonManager {

    private static final String JSON_LABEL_NAME = "name";
    private static final String JSON_LABEL_EMAIL = "email";
    private static final String JSON_LABEL_GENDER = "gender";
    private static final String JSON_LABEL_BIRTHDATE = "birthDate";
    private static final String JSON_LABEL_PASSWORD = "password";
    private static final String JSON_LABEL_RECEIVERMAIL = "receiverMail";

    public String name;
    public String mail;
    public String gender;
    public String birthDate;
    public String password;
    public String receiverMail;

    public User(){

    }

    public User(JSONObject jsonObject){
        name = getString(JSON_LABEL_NAME, jsonObject);
        mail = getString(JSON_LABEL_EMAIL, jsonObject);
        gender = getString(JSON_LABEL_GENDER, jsonObject);
        birthDate = getString(JSON_LABEL_BIRTHDATE, jsonObject);
        password = getString(JSON_LABEL_PASSWORD, jsonObject);
        receiverMail = getString(JSON_LABEL_RECEIVERMAIL, jsonObject);
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put(JSON_LABEL_NAME,name);
            jsonObject.put(JSON_LABEL_EMAIL,mail);
            jsonObject.put(JSON_LABEL_GENDER,gender);
            jsonObject.put(JSON_LABEL_BIRTHDATE,birthDate);
            jsonObject.put(JSON_LABEL_PASSWORD,password);
            jsonObject.put(JSON_LABEL_RECEIVERMAIL,receiverMail);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

}
