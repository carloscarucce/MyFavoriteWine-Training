package com.sample.gpd.myfavoritewine.service.listener;

import com.sample.gpd.myfavoritewine.service.model.Error;

/**
 * Created by kauesantoja on 17/10/2015.
 */
public interface OperationalListener <TResult> {

    public void onOperationalSuccess(final TResult result);
    public void onOperationalError(final TResult result, Error error);

}
