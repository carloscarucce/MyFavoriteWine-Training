package com.sample.gpd.myfavoritewine.service.model;

import java.util.Calendar;

/**
 * Created by kauesantoja on 17/10/2015.
 */
public class UserResponse extends User{

    private long requestAt;

    public void setRequestAt(){

        Calendar calendar = Calendar.getInstance();
        requestAt = calendar.getTimeInMillis();

    }

    public long getRequestAt(){
        return requestAt;
    }
}
