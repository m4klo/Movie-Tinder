package com.example.tinder_movie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLoginRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_registration);

        Button mLogin = findViewById(R.id.login);
        Button mRegister = findViewById(R.id.register);

        mLogin.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseLoginRegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        mRegister.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseLoginRegistrationActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        });
    }
}