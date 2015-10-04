package com.sample.gpd.myfavoritewine.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.sample.gpd.myfavoritewine.R;
import com.sample.gpd.myfavoritewine.ui.fragment.LoginFragment;
import com.sample.gpd.myfavoritewine.ui.fragment.RegisterFragment;

/**
 * Created by kauesantoja on 26/09/2015.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);

        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,registerFragment).commit();

    }
}
