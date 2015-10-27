package com.sample.gpd.myfavoritewine.service.business;

import com.sample.gpd.myfavoritewine.service.util.MongolabManager;

/**
 * Created by kauesantoja on 17/10/2015.
 */
abstract class BaseBusiness {

    protected MongolabManager mMongolabManager;

    public BaseBusiness(){

        mMongolabManager = new MongolabManager();

    }

}
