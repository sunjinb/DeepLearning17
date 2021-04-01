package com.example.newcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public void buttonCallClicked(View v){
        String tel = editText.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+tel)));

    }
    public void buttonNumClicked(View v){
        String text = editText.getText().toString();
        String num = ((Button)v).getText().toString();

        text += num;

        editText.setText(text);

    }
    public void buttonDelClicked(View v){

        String text = editText.getText().toString();
        if (text.length() >0){
            text = text.substring(0, text.length() -1);
            editText.setText(text);
        }

    }
    public void buttonClearClicked(View v){
        editText.setText("");
    }
}