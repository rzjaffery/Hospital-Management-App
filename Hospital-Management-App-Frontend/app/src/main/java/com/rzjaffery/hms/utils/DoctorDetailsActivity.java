package com.rzjaffery.hms.utils;

import com.google.firebase.database.DatabaseReference;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rzjaffery.hms.R;
import com.rzjaffery.hms.data.model.Doctor;

public class DoctorDetailsActivity extends AppCompatActivity {

    TextView tvName, tvSpecialization, tvTimings, tvEmail;
    Button btnBook;
    DatabaseReference doctorRef;
    String doctorId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tvName = findViewById(R.id.tvDoctorName);
        tvSpecialization = findViewById(R.id.tvDoctorSpecialization);
        tvTimings = findViewById(R.id.tvDoctorTimings);
        tvEmail = findViewById(R.id.tvDoctorEmail);
        btnBook = findViewById(R.id.btnBookAppointment);

        doctorId = getIntent().getStringExtra("doctorId");
        doctorRef = FirebaseDatabase.getInstance().getReference("users").child(doctorId);

        doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Doctor doctor = snapshot.getValue(Doctor.class);
                if (doctor != null) {
                    tvName.setText(doctor.getName());
                    tvSpecialization.setText(doctor.getSpecialization());
                    tvTimings.setText(doctor.getTimings());
                    tvEmail.setText(doctor.getEmail());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(DoctorDetailsActivity.this, AppointmentBookingActivity.class);
            intent.putExtra("doctorId", doctorId);
            startActivity(intent);
        });
    }
}
