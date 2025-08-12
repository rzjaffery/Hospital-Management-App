package com.rzjaffery.hms.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rzjaffery.hms.R;

public class LoginActivity extends AppCompatActivity {

    EditText eEmail, ePassword;
    Button bLogin;
    TextView tRegister;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        bLogin=findViewById(R.id.btnLogin);
        tRegister=findViewById(R.id.tvGoToRegister);

        bLogin.setOnClickListener(
                e ->{
                    String email = eEmail.getText().toString().trim();
                    String password = ePassword.getText().toString().trim();

                    if(email.isEmpty() || password.isEmpty()){
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        // TODO: Call Spring Boot backend API for login
                        Toast.makeText(this, "Login Successful (Temporary)", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        tRegister.setOnClickListener(
                r -> {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
        );
    }
}
