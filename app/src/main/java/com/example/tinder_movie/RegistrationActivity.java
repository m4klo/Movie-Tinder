package com.example.tinder_movie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity {

    private Button mRegister;
    private TextView mText;
    private EditText mEmail, mPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = firebaseAuth -> {
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user !=null){
                Intent intent = new Intent(RegistrationActivity.this, GenreActivity.class);
                startActivity(intent);
                finish();
            }
        };


        Button mRegister = findViewById(R.id.register);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mText = (TextView) findViewById(R.id.textView2);

        mRegister.setOnClickListener(view -> {
            final String email = mEmail.getText().toString();
            final String password = mPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, task -> {
                if(!task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                }else{
                    String userId = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUserDb = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users").child(userId);
                    currentUserDb.setValue("");
                }

            });
        });
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}