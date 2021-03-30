package com.example.call_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

import static android.widget.Toast.LENGTH_LONG;

public class UserInformationActivity extends AppCompatActivity {
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);



        Button register2_Button = findViewById(R.id.register2_button);

        register2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ((EditText) findViewById(R.id.name_register)).getText().toString();
                String year = ((EditText)findViewById(R.id.year_register)).getText().toString();
                String month = ((EditText)findViewById(R.id.month_register)).getText().toString();
                String day = ((EditText)findViewById(R.id.day_register)).getText().toString();
                String phoneNumber = ((EditText)findViewById(R.id.phone_register)).getText().toString();

                if(name.length()==0){
                    startToast("이름을 입력하세요.");
                }else if(year.length()==0||month.length()==0||day.length()==0){
                    startToast("생년월일을 정확히 입력하세요.");
                }else if(gender.length()==0){
                    startToast("성별을 선택하세요.");
                }else if(phoneNumber.length()==0){
                    startToast("전화번호를 입력하세요.");
                }else{
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("day", day);
                    intent.putExtra("gender", gender);
                    intent.putExtra("phoneNumber", phoneNumber);



                    startActivity(intent);
                }
            }
        });

    }
    public void maleViewClicked(View v){
        View maleView = findViewById(R.id.maleView);
        View femaleView = findViewById(R.id.femaleView);
        maleView.setBackgroundResource(R.drawable.colored_border);
        femaleView.setBackgroundResource(R.drawable.border);
        gender = "male";
    }

    public void femaleViewClicked(View v){
        View maleView = findViewById(R.id.maleView);
        View femaleView = findViewById(R.id.femaleView);
        maleView.setBackgroundResource(R.drawable.border);
        femaleView.setBackgroundResource(R.drawable.colored_border);
        gender = "female";
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}