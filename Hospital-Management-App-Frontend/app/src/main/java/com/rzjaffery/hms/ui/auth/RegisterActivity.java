package com.rzjaffery.hms.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword, etConfirmPassword;
    Spinner spinnerRole;
    CheckBox cbTerms;
    Button btnRegister;

    FirebaseAuth auth;
    FirebaseFirestore db;


    String[] roles = {"Select Role", "Admin", "Patient", "Doctor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etRegName);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        etConfirmPassword = findViewById(R.id.etRegConfirmPassword);
        spinnerRole = findViewById(R.id.etRole);
        cbTerms = findViewById(R.id.etCheck);
        btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        spinnerRole.setAdapter(adapter);

        btnRegister.setOnClickListener(v -> validateAndRegister());
    }

    private void validateAndRegister() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String role = spinnerRole.getSelectedItem().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (role.equals("Select Role")) {
            Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cbTerms.isChecked()) {
            Toast.makeText(this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send to backend API
        registerUser(name, email, password, role);
    }

    private void registerUser(String name, String email, String password, String role) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = auth.getCurrentUser().getUid();

                        // Save extra user info in Firestore
                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("name", name);
                        userMap.put("email", email);
                        userMap.put("role", role);

                        db.collection("users").document(uid).set(userMap)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
                                    redirectToDashboard(role, name);
                                });
                    } else {
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
