package com.example.call_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register_Button = findViewById(R.id.register_button);
        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //UI
    }

    private void register(){
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String gender = intent.getExtras().getString("gender");
        String year = intent.getExtras().getString("year");
        String month = intent.getExtras().getString("month");
        String day = intent.getExtras().getString("day");
        String phoneNumber = intent.getExtras().getString("phoneNumber");

        String email = ((EditText)findViewById(R.id.email_register)).getText().toString();
        String nickname = ((EditText)findViewById(R.id.nickname_register)).getText().toString();
        String password = ((EditText)findViewById(R.id.password_register)).getText().toString();
        String password_check = ((EditText)findViewById(R.id.password_check)).getText().toString();



        if(email.length()>0 && password.length()>0 && password_check.length()>0){
            if(password.equals(password_check)){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    mAuth.signOut();

                                    rootNode = FirebaseDatabase.getInstance();
                                    reference = rootNode.getReference("users");

                                    RegisterHelper registerHelper = new RegisterHelper(email, password, name, year, month, day, gender, phoneNumber);
                                    reference.child(phoneNumber).setValue(registerHelper);

                                    startToast("회원가입 되었습니다.");
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);//액티비티 스택제거
                                    startActivity(intent);
                                }
                                else{
                                    //예외 처리
                                    if(task.getException().toString().contains("The email address is already in use by another account")) {
                                        startToast("이미 존재하는 계정입니다.");
                                    }else if(task.getException().toString().contains("The email address is badly formatted"))
                                    {
                                        startToast("이메일 형식이 아닙니다.");
                                    }else if(task.getException().toString().contains("Password should be at least 6 characters")){
                                        startToast("비밀번호를 6자 이상으로 입력하세요.");
                                    }else{
                                        startToast(task.getException().toString());
                                    }
                                }
                            }
                        });
            }else{
                startToast("비밀번호가 일치하지 않습니다.");
            }
        }else{
            startToast("아이디 또는 비밀번호를 입력하시오.");
        }
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}