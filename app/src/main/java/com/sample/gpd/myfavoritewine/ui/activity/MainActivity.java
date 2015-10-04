package com.sample.gpd.myfavoritewine.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sample.gpd.myfavoritewine.R;
import com.sample.gpd.myfavoritewine.ui.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,loginFragment).commit();

    }


}
