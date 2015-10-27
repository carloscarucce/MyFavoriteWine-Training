package com.sample.gpd.myfavoritewine.service.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.sample.gpd.myfavoritewine.R;
import com.sample.gpd.myfavoritewine.service.business.UserBusiness;
import com.sample.gpd.myfavoritewine.service.listener.OperationalListener;
import com.sample.gpd.myfavoritewine.service.model.*;
import com.sample.gpd.myfavoritewine.service.model.Error;
import com.sample.gpd.myfavoritewine.service.util.NetworkUtil;

/**
 * Created by kauesantoja on 17/10/2015.
 */
public class UserManager {

    private Context mContext;

    public UserManager(Context context){
        mContext = context;
    }

    public void register(User user, final OperationalListener <UserResponse> callback){

        if(!NetworkUtil.isConnectionAvailable(mContext)){

            Error error = new Error();
            error.errorMessage = mContext.getResources().getString(R.string.label_error_message_connection_fail);
            error.errorCode = Error.NO_INTERNET_AVAILABLE;
            callback.onOperationalError(null,error);
            return;
        }

        AsyncTask <User, Void, OperationalResult<UserResponse>> asyncTask = new AsyncTask<User, Void, OperationalResult<UserResponse>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected OperationalResult<UserResponse> doInBackground(User... users) {
                UserBusiness userBusiness = new UserBusiness();
                return userBusiness.register(users[0]);
            }

            @Override
            protected void onPostExecute(OperationalResult<UserResponse> userResponse) {
                super.onPostExecute(userResponse);

                if (userResponse.operationalResultResponseType == OperationalResult.OperationalResultResponseType.SUCCESS){
                    callback.onOperationalSuccess(userResponse.result);
                } else {
                    callback.onOperationalError(null, userResponse.error);
                }

            }
        }.execute(user);
    }

}
