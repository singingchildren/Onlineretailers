package com.example.onlineretailersapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.RegisterBean;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.utils.ImmersiveUtil;
import com.example.onlineretailersapp.utils.IsHaveNetWork;
import com.example.onlineretailersapp.utils.SPFUtil;
import com.example.onlineretailersapp.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity<T> extends AppCompatActivity implements IView<T> {

    @BindView(R.id.register_cell_phone_num)
    EditText registerCellPhoneNum;
    @BindView(R.id.register_verification_code)
    EditText registerVerificationCode;
    @BindView(R.id.register_login_password)
    EditText registerLoginPassword;
    @BindView(R.id.register_hidepass)
    ImageView register_hidepass;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.go_login)
    TextView goLogin;
    private IPrsenterImpl iPrsenter;
    private Map<String, String> map;
    private String phoneStr;
    private String passwStr;
    private SPFUtil spfUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        isHidePass();
        iPrsenter = new IPrsenterImpl(this);
        spfUtil = new SPFUtil();
        ImmersiveUtil.setStatusColor(this);
        ImmersiveUtil.setSystemInvadeBlack(this);
    }

    @Override
    public void onSueecss(T data) {
        if (data instanceof RegisterBean) {
            RegisterBean registerBean = (RegisterBean) data;
            if (registerBean.getStatus().equals("0000")) {
                Toast.makeText(RegisterActivity.this, registerBean.getMessage() + "", Toast.LENGTH_SHORT).show();
                finish();
                spfUtil.clear(RegisterActivity.this);
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                LoginActivity.instance.finish();

            } else {
                Toast.makeText(RegisterActivity.this, registerBean.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Nothing to do
        }
    }

    @Override
    public void onError(T error) {

    }

    @OnClick({R.id.register_btn,R.id.go_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                if (IsHaveNetWork.isNetworkConnected(this)) {
                    phoneStr = registerCellPhoneNum.getText().toString().trim();
                    passwStr = registerLoginPassword.getText().toString().trim();
                    map = new HashMap<>();
                    map.put("phone", phoneStr);
                    map.put("pwd", passwStr);
                    iPrsenter.startRequest(Contacts.REGISTER_URL, map, RegisterBean.class, 2, null, null);
                } else {
                    Toast.makeText(RegisterActivity.this, "没网啦 亲 请检查一下网络设置", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.go_login:
                spfUtil.clear(RegisterActivity.this);
                finish();
                break;
        }
    }

    //密码明文密文之间转换
    private void isHidePass() {
        register_hidepass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    registerLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    registerLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
    }
}
