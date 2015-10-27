package com.sample.gpd.myfavoritewine.service.model;

/**
 * Created by kauesantoja on 17/10/2015.
 */
public class OperationalResult <TResult> {
    public enum OperationalResultResponseType {
        SUCCESS, ERROR
    }

    public OperationalResultResponseType operationalResultResponseType;

    public TResult result;

    public Error error;

}
