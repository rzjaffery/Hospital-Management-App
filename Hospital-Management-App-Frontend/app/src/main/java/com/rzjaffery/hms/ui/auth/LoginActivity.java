package com.rzjaffery.hms.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rzjaffery.hms.R;
import com.rzjaffery.hms.data.model.User;
import com.rzjaffery.hms.network.ApiClient;
import com.rzjaffery.hms.network.ApiService;
import com.rzjaffery.hms.ui.admin.AdminDashboardActivity;
import com.rzjaffery.hms.ui.doctor.DoctorDashboardActivity;
import com.rzjaffery.hms.ui.patient.PatientDashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        bLogin = findViewById(R.id.btnLogin);
        tRegister = findViewById(R.id.tvGoToRegister);

        bLogin.setOnClickListener(v -> {
            String email = eEmail.getText().toString().trim();
            String password = ePassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(email, password);
            }
        });

        tRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(String email, String password) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        User loginRequest = new User();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Call<User> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User loggedInUser = response.body();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    // Get role from response
                    String role = loggedInUser.getRole();
                    String username = loggedInUser.getName();

                    // Redirect based on role
                    Intent intent;
                    switch (role.toLowerCase()) {
                        case "admin":
                            intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                            break;
                        case "doctor":
                            intent = new Intent(LoginActivity.this, DoctorDashboardActivity.class);
                            break;
                        case "patient":
                            intent = new Intent(LoginActivity.this, PatientDashboardActivity.class);
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Unknown role: " + role, Toast.LENGTH_SHORT).show();
                            return;
                    }

                    intent.putExtra("username", username);
                    intent.putExtra("role", role);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
