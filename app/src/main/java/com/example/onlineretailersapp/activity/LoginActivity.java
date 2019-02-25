package com.example.onlineretailersapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.LoginBean;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.utils.ImmersiveUtil;
import com.example.onlineretailersapp.utils.IsHaveNetWork;
import com.example.onlineretailersapp.utils.RegexUtil;
import com.example.onlineretailersapp.utils.SPFUtil;
import com.example.onlineretailersapp.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity<T> extends AppCompatActivity implements IView<T> {

    @BindView(R.id.cell_phone_num)
    EditText cellPhoneNum;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.jzPass)
    CheckBox jzPass;
    @BindView(R.id.hidepass)
    ImageView hidepass;
    @BindView(R.id.goRegister)
    TextView goRegister;
    private IPrsenterImpl iPrsenter;
    private String phoneStr;
    private String passwStr;
    private SPFUtil spfUtil;
    public static LoginActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instance = this;
        ButterKnife.bind(this);
        iPrsenter = new IPrsenterImpl(this);
        //封装了一个SharedPreferences工具类 /dy
        spfUtil = new SPFUtil();
        //如果记住
        jzSueecss();
        //点击查看密码
        isHidePass();
        ImmersiveUtil.setStatusColor(this);
        ImmersiveUtil.setSystemInvadeBlack(this);
    }


    @Override
    public void onSueecss(T data) {
        if (data instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) data;
            if (loginBean.getStatus().equals("0000")) {
                Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();
                //判断记住密码复选框是否选中 该逻辑要放在 登录成功之后
                isJz();
                userMsg(loginBean);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                //销毁该页面
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Nothing to do
        }
    }

    private void userMsg(LoginBean loginBean) {
        int userId = loginBean.getResult().getUserId();
        String sessionId = loginBean.getResult().getSessionId();
        spfUtil.putString(this,"userId",userId+"");
        spfUtil.putString(this,"sessionId",sessionId);

    }


    @Override
    public void onError(T error) {

    }


    @OnClick({R.id.goRegister, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                login_btn();
                break;
            case R.id.goRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    //点击登录按钮
    private void login_btn() {
        if (IsHaveNetWork.isNetworkConnected(this)) {
            phoneStr = cellPhoneNum.getText().toString().trim();
            passwStr = loginPassword.getText().toString().trim();
            Map<String, String> map = new HashMap<>();
            map.put("phone", phoneStr);
            map.put("pwd", passwStr);

            if (!phoneStr.isEmpty() && !passwStr.isEmpty()) {
                boolean b = RegexUtil.checkMobile(phoneStr);
                if (b) {
                    iPrsenter.startRequest(Contacts.LOGIN_URL, map, LoginBean.class, 1, null, null);
                } else {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "没网啦 亲 请检查一下网络设置", Toast.LENGTH_SHORT).show();
        }

    }

    //判断是否记住密码
    private void isJz() {
        if (jzPass.isChecked()) {
            spfUtil.putString(LoginActivity.this, "phoneJz", phoneStr);
            spfUtil.putString(LoginActivity.this, "passJz", passwStr);
            spfUtil.putBoolean(LoginActivity.this, "remember", true);
        } else {
            spfUtil.clear(LoginActivity.this);
        }
    }

    //成功记住密码
    private void jzSueecss() {
        String phoneJz = spfUtil.getString(LoginActivity.this,"phoneJz", "");
        String passJz =  spfUtil.getString(LoginActivity.this,"passJz", "");
        boolean remember = spfUtil.getBoolean(LoginActivity.this,"remember", false);
        if (remember) {
            cellPhoneNum.setText(phoneJz);
            loginPassword.setText(passJz);
            jzPass.setChecked(true);
        }
    }

    //密码明文密文之间转换
    private void isHidePass() {
        hidepass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
    }
}
