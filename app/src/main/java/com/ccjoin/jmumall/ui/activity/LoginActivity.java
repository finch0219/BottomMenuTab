package com.ccjoin.jmumall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.ccjoin.jmumall.R;
import com.ccjoin.jmumall.mvp.model.UserBean;
import com.ccjoin.jmumall.mvp.presenter.UserLoginPresenter;
import com.ccjoin.jmumall.mvp.view.IUserLoginView;

/**
 * implements IUserLoginView 此处需要实现一个与View相关的接口
 */
public class LoginActivity extends AppCompatActivity implements IUserLoginView {

    private EditText mEtUserName;
    private EditText mEtPassword;

    private Button mBtnLogin;
    private Button mBtnClear;

    private ProgressBar mPbLoading;

    /** 在Activity中需要注入的Presenter */
    private UserLoginPresenter mPresenter = new UserLoginPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }


    private void initViews() {
        mEtUserName = (EditText) findViewById(R.id.et_user_name_value);
        mEtPassword = (EditText) findViewById(R.id.et_pwd_value);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnClear = (Button) findViewById(R.id.btn_clear);

        mPbLoading = (ProgressBar) findViewById(R.id.pb_loading);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mPresenter.login();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mPresenter.clear();
            }
        });
    }


    @Override public String getUserName() {
        return mEtUserName.getText().toString();
    }


    @Override public String getPassword() {
        return mEtPassword.getText().toString();
    }


    @Override public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }


    @Override public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }


    @Override public void toPersonCenter(UserBean userBean) {
        Intent intent = new Intent();
        intent.putExtra("isLogin", true);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void showFailedError() {
        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
    }


    @Override public void clearUserName() {
        mEtUserName.setText("");
    }


    @Override public void clearPassword() {
        mEtPassword.setText("");
    }
}
