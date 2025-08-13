package com.rzjaffery.hms.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
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
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.btnLogin);
        tRegister = findViewById(R.id.tvGoToRegister);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        bLogin.setOnClickListener(v -> {
            String email = eEmail.getText().toString().trim();
            String password = ePassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                loginUser();
            }
        });

        tRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser() {
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = auth.getCurrentUser().getUid();
                        db.collection("users").document(uid).get()
                                .addOnSuccessListener(doc -> {
                                    String name = doc.getString("name");
                                    String role = doc.getString("role");
                                    redirectToDashboard(role, name);
                                });
                    } else {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void redirectToDashboard(String role, String name) {
        Intent intent;
        switch (role.toLowerCase()) {
            case "admin":
                intent = new Intent(this, AdminDashboardActivity.class);
                break;
            case "doctor":
                intent = new Intent(this, DoctorDashboardActivity.class);
                break;
            default:
                intent = new Intent(this, PatientDashboardActivity.class);
                break;
        }
        intent.putExtra("username", name);
        startActivity(intent);
        finish();
    }

}
