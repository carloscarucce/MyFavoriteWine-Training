package com.sample.gpd.myfavoritewine.service.util;

import android.util.Log;

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

}
