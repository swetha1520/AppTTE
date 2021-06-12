package com.example.tte;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MobileActivity extends AppCompatActivity {
    private EditText phoneNumber;
    private Button sendOTP;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        phoneNumber = findViewById(R.id.phoneNumber);
        sendOTP = findViewById(R.id.sendOtp);
        sendOTP.setEnabled(false);
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==10)
                    sendOTP.setEnabled(true);
                if(s.length()!=10)
                    sendOTP.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumber.getText().toString().trim();
                Intent intent = new Intent(MobileActivity.this,VerificationActivity.class);
                intent.putExtra("phone","+91"+phone);
                startActivity(intent);
            }
        });
    }

}