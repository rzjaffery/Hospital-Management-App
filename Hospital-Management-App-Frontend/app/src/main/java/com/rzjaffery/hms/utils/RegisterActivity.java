package com.rzjaffery.hms.utils;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rzjaffery.hms.R;

public class RegisterActivity extends AppCompatActivity {

    EditText eName, eEmail, ePassword, eCPassword;
    Spinner eRole;
    CheckBox eCheckbox;
    Button btnRegister;
    TextView tLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eName=findViewById(R.id.etRegName);
        eEmail=findViewById(R.id.etRegEmail);
        ePassword=findViewById(R.id.etRegPassword);
        eCPassword=findViewById(R.id.etRegConfirmPassword);
        eRole=findViewById(R.id.etRole);
        eCheckbox= findViewById(R.id.etCheck);
        btnRegister=findViewById(R.id.btnRegister);
        tLogin=findViewById(R.id.tvGoToLogin);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eRole.setAdapter(adapter);


        btnRegister.setOnClickListener(v -> {
            String name = eName.getText().toString().trim();
            String email = eEmail.getText().toString().trim();
            String password = ePassword.getText().toString().trim();
            String confirmPassword = eCPassword.getText().toString().trim();
            String role = eRole.getSelectedItem().toString();
            boolean checked = eCheckbox.isChecked();

            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role.isEmpty() || !checked){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Call Spring Boot backend API for registration
                Toast.makeText(this, "Registration Successful (Temporary)", Toast.LENGTH_SHORT).show();
            }
        });

        tLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}
