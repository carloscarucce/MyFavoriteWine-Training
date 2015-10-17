package com.sample.gpd.myfavoritewine.service.business;

import com.sample.gpd.myfavoritewine.service.model.OperationalResult;
import com.sample.gpd.myfavoritewine.service.model.User;
import com.sample.gpd.myfavoritewine.service.model.UserResponse;

/**
 * Created by kauesantoja on 17/10/2015.
 */
public class UserBusiness extends BaseBusiness {

    public UserBusiness(){
        super();
    }

    public OperationalResult <UserResponse> register(User user){

        OperationalResult<UserResponse> result = new OperationalResult<UserResponse>();
        User response = mMongolabManager.register(user);

        if (response != null){
            result.operationalResultResponseType = OperationalResult.OperationalResultResponseType.SUCCESS;
            UserResponse userResponse = new UserResponse();
            userResponse.name = response.name;
            userResponse.mail = response.mail;
            userResponse.birthDate = response.birthDate;
            userResponse.gender = response.gender;
            userResponse.password = response.password;
            userResponse.setRequestAt();
            result.result = userResponse;
        } else {
            result.operationalResultResponseType = OperationalResult.OperationalResultResponseType.ERROR;
        }

        return result;
    }

}
