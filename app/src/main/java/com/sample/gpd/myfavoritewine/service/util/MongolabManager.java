package com.sample.gpd.myfavoritewine.service.util;

import android.util.Log;

import com.sample.gpd.myfavoritewine.service.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kauesantoja on 03/10/2015.
 */
public class MongolabManager {

    private static final String TAG = MongolabManager.class.getName();
    public static final String TYPE_METHOD_POST = "POST";
    public static final String TYPE_METHOD_GET = "GET";

    public static final String API_KEY = "?apiKey=vCGIaC1cfLndci-iH7wxrgvX1q_DO73v";
    public static final String SERVER_URL = "https://api.mongolab.com/api/1/databases";
    public static final String SERVER_BASENAME = "/myfavoritewine";
    public static final String COLLECTIONS = "/collections";
    public static final String COLLECTION_USER = "/user";
    public static final String COLLECTION_WINE = "/wine";

    public String doRequest(String url, String typeMethod, String params) {

        String result = null;
        try{

            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod(typeMethod);
            if(TYPE_METHOD_GET.equals(typeMethod)){
                conn.setDoInput(false);
                conn.connect();
            }else if(TYPE_METHOD_POST.equals(typeMethod)){
                conn.setRequestProperty("Content-Type","application/json");
                conn.setDoInput(true);
                conn.connect();
                OutputStream out = conn.getOutputStream();
                byte[] bytes = params.getBytes("UTF8");
                out.write(bytes);
                out.flush();
                out.close();
            }

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = conn.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;

                while((len = in.read(buffer)) > 0){
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                in.close();
                result = new String(bytes);
            }

        }catch (MalformedURLException e){
            Log.e(TAG,e.getMessage());
        }catch (IOException e){
            Log.e(TAG,e.getMessage());
        }

        return result;

    }

    public User register(User user){
        User result = null;

        JSONObject document = new JSONObject();
        try{
            String childRequest = "document";
            document.put(childRequest, user.toJson());
            StringBuffer url = new StringBuffer();
            url.append(SERVER_URL);
            url.append(SERVER_BASENAME);
            url.append(COLLECTIONS);
            url.append(COLLECTION_USER);
            url.append(API_KEY);
            String response = doRequest(url.toString(),TYPE_METHOD_POST, document.toString());
            JSONObject jsonObjectDocument = new JSONObject(response);
            JSONObject jsonObject = jsonObjectDocument.getJSONObject(childRequest);
            result = new User(jsonObject);

        }catch (JSONException je){
            je.printStackTrace();
        }

        return result;
    }

}
