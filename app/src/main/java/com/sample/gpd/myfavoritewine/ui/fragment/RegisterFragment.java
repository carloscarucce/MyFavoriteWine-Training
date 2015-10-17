package com.sample.gpd.myfavoritewine.ui.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface.OnDismissListener;

import com.sample.gpd.myfavoritewine.R;
import com.sample.gpd.myfavoritewine.service.listener.OperationalListener;
import com.sample.gpd.myfavoritewine.service.manager.UserManager;
import com.sample.gpd.myfavoritewine.service.model.*;
import com.sample.gpd.myfavoritewine.ui.activity.RegisterActivity;
import com.sample.gpd.myfavoritewine.ui.widget.custom.DatePickerDialog;
import com.sample.gpd.myfavoritewine.service.model.Error;

/**
 * Created by kauesantoja on 03/10/2015.
 */
public class RegisterFragment extends Fragment{
    private RegisterActivity mRegisterActivity;

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private RadioGroup mRadioGroupGender;
    private EditText mEditTextBirthday;
    private CheckBox mCheckBoxReceivePromotional;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordConfirm;
    private Button mButtonRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,null,false);

        mEditTextName = (EditText) view.findViewById(R.id.edittext_name);
        mEditTextEmail = (EditText) view.findViewById(R.id.edittext_email);
        mRadioGroupGender = (RadioGroup) view.findViewById(R.id.radiogroup_gender);
        mEditTextBirthday = (EditText) view.findViewById(R.id.edittext_birthday);
        mEditTextBirthday.setOnFocusChangeListener(mOnFocusChangeListener);

        mCheckBoxReceivePromotional = (CheckBox) view.findViewById(R.id.checkbox_receive_promotional);
        mEditTextPassword = (EditText) view.findViewById(R.id.edittext_password);
        mEditTextPasswordConfirm = (EditText) view.findViewById(R.id.edittext_password_confirm);
        mButtonRegister = (Button) view.findViewById(R.id.button_register);
        mButtonRegister.setOnClickListener(mOnClickListener);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mRegisterActivity = (RegisterActivity) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureToolbar();
    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) mRegisterActivity.findViewById(R.id.toolbar);
        mRegisterActivity.setSupportActionBar(toolbar);
        mRegisterActivity.getSupportActionBar().setTitle(R.string.toolbar_label_title_register);
        mRegisterActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRegisterActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable upArrowIcon = ContextCompat.getDrawable(mRegisterActivity,R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrowIcon.setColorFilter(mRegisterActivity.getResources().getColor(R.color.material_white), PorterDuff.Mode.SRC_ATOP);
        mRegisterActivity.getSupportActionBar().setHomeAsUpIndicator(upArrowIcon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterActivity.finish();
            }
        });
    }

    private boolean isRequiredField (EditText editText) {
        boolean result = true;
        if (editText.getText().toString().isEmpty()) {
            editText.setError(mRegisterActivity.getString(R.string.label_error_message_field_required));
            result = false;
        }
        return result;
    }

    private boolean isValidMail (EditText editText) {
        boolean result = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches()){
            editText.setError(mRegisterActivity.getString(R.string.label_error_message_invalid_mail));
            result = false;
        }
        return result;
    }

    /**
     *listeners
     */

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean resultForms;
            resultForms = isRequiredField(mEditTextName);
            resultForms = resultForms && isValidMail(mEditTextEmail);
            if(resultForms){
                final User user = new User();
                user.name = mEditTextName.getText().toString();
                user.mail = mEditTextEmail.getText().toString();

                user.gender = (mRadioGroupGender.getCheckedRadioButtonId() == R.id.radiobutton_male)? "Masculino" : "Feminino";

                user.birthDate = mEditTextBirthday.getText().toString();
                user.password = mEditTextPassword.getText().toString();
                user.receiverMail = mCheckBoxReceivePromotional.isChecked() ? "Sim" : "Não";
                UserManager userManager = new UserManager(mRegisterActivity);
                userManager.register(user, new OperationalListener<UserResponse>() {
                    @Override
                    public void onOperationalSuccess(UserResponse userResponse) {
                        Toast.makeText(mRegisterActivity, "SUCESSO", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onOperationalError(UserResponse userResponse, Error error) {
                        Toast.makeText(mRegisterActivity, error.errorMessage, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    };


    private View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {

            if(hasFocus){
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.setOnDateSetListener(mOnDateSetListener);
                datePickerDialog.setOnDismissListener(mOnDismissListener);
                datePickerDialog.show(getFragmentManager(), mRegisterActivity.getString(R.string.label_date_birthday));
            }

        }
    };

    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            StringBuffer dateSelected = new StringBuffer();
            dateSelected.append(dayOfMonth);
            dateSelected.append("/");
            dateSelected.append(month);
            dateSelected.append("/");
            dateSelected.append(year);
            mEditTextBirthday.setText(dateSelected.toString());
            //xesuss boas praticas
        }
    };

    private OnDismissListener mOnDismissListener = new OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            mEditTextPassword.requestFocus();
        }
    };
}
