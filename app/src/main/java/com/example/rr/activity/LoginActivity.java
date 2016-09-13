package com.example.rr.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rr.R;
import com.example.rr.View.LoginView;
import com.example.rr.entity.UserInfos;
import com.example.rr.presenter.LoginPresenter;
import com.example.rr.presenter.LoginPresenterImpel;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mzp on 2016/9/6.
 */
public class LoginActivity extends Activity implements LoginView
{
    private LoginPresenter loginPresenter;

    private EditText etUserName;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login);

        loginPresenter = new LoginPresenterImpel(this, this);
    }

    @OnClick(R.id.bt_login)
    public void onClick()
    {
        loginPresenter.loginPresenter();
    }

    @Override
    public String getUserName()
    {
        return etUserName.getText().toString().trim();
    }

    @Override
    public String getPassword()
    {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void loginSuccess(UserInfos userInfos)
    {
        Toast.makeText(LoginActivity.this, "requestSuccess" + userInfos.getMobile(), Toast.LENGTH_SHORT).show();
    }
}
