package com.rzjaffery.hms.ui.doctor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.rzjaffery.hms.R;
import com.rzjaffery.hms.utils.FirebaseHelper;

import java.util.HashMap;
import java.util.Map;

public class DoctorProfileActivity extends AppCompatActivity {

    EditText etEmail, etSpecialization, etTimings;
    TextView tvName, tvRole;
    Button btnSave;
    DatabaseReference userRef;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);

        tvName = findViewById(R.id.tvName);
        tvRole = findViewById(R.id.tvRole);
        etEmail = findViewById(R.id.etEmail);
        etSpecialization = findViewById(R.id.etSpecialization);
        etTimings = findViewById(R.id.etTimings);
        btnSave = findViewById(R.id.btnSave);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userRef = FirebaseHelper.getUsersReference().child(uid);

        loadProfile();

        btnSave.setOnClickListener(v -> saveProfile());
    }

    private void loadProfile() {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String role = snapshot.child("role").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String specialization = snapshot.child("specialization").getValue(String.class);
                    String timings = snapshot.child("timings").getValue(String.class);

                    tvName.setText(name != null ? name : "");
                    tvRole.setText(role != null ? role : "");
                    etEmail.setText(email != null ? email : "");
                    etSpecialization.setText(specialization != null ? specialization : "");
                    etTimings.setText(timings != null ? timings : "");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DoctorProfileActivity.this, "Error loading profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveProfile() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("email", etEmail.getText().toString().trim());
        updates.put("specialization", etSpecialization.getText().toString().trim());
        updates.put("timings", etTimings.getText().toString().trim());

        userRef.updateChildren(updates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error updating profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
