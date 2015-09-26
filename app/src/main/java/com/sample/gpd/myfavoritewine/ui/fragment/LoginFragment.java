package com.sample.gpd.myfavoritewine.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.gpd.myfavoritewine.R;
import com.sample.gpd.myfavoritewine.ui.activity.MainActivity;

/**
 * Created by kauesantoja on 26/09/2015.
 */
public class LoginFragment extends Fragment{

    private MainActivity mMainActivity;

    private EditText mEditTextLogin;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private TextView mTextViewRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,null,false);
        mEditTextLogin = (EditText) view.findViewById(R.id.edittext_login);
        mEditTextPassword = (EditText) view.findViewById(R.id.edittext_password);
        mButtonLogin = (Button) view.findViewById(R.id.button_login);
        mTextViewRegister = (TextView) view.findViewById(R.id.textview_register);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivity = (MainActivity) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureToolBar();
    }

    private void configureToolBar(){
        Toolbar toolbar = (Toolbar) mMainActivity.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
    }

}
