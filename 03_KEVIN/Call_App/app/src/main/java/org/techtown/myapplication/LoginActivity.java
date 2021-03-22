package org.techtown.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = findViewById(R.id.login_button);
        Button signup_button = findViewById(R.id.signup_button);
        Button menu_button = findViewById(R.id.menu_button);
        Button logout_button = findViewById(R.id.logout_button);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Kakaologin.class);
                startActivity(intent);
            }
        });

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그아웃에 성공하였습니다.",Toast.LENGTH_LONG).show();
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        menu_button.setVisibility(View.GONE);
                        logout_button.setVisibility(View.GONE);
                        login_button.setVisibility(View.VISIBLE);
                        signup_button.setVisibility(View.VISIBLE);
                        return null;
                    }
                });
            }
        });


        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user != null){   //로그인 되어있음
                    menu_button.setVisibility(View.VISIBLE);
                    logout_button.setVisibility(View.VISIBLE);
                    login_button.setVisibility(View.GONE);
                    signup_button.setVisibility(View.GONE);
                } else{             //로그인 안 되어있음
                    menu_button.setVisibility(View.GONE);
                    logout_button.setVisibility(View.GONE);
                    login_button.setVisibility(View.VISIBLE);
                    signup_button.setVisibility(View.VISIBLE);
                }
                return null;
            }
        });
    }

}