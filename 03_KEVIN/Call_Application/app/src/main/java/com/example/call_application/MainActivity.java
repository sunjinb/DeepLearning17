package com.example.call_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private MainBackPressCloseHandler mainBackPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBackPressCloseHandler = new MainBackPressCloseHandler(this);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Toast.makeText(this, "로그인을 하시오", Toast.LENGTH_LONG).show();
            startLoginActivity();
        }

        Button logout_Button = findViewById(R.id.logout_button);

        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);//액티비티 스택제거
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        mainBackPressCloseHandler.onBackPressed();
    }
}