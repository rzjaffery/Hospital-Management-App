package com.rzjaffery.hms.utils;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rzjaffery.hms.R;

import java.util.HashMap;
import java.util.Map;


public class AppointmentBookingActivity extends AppCompatActivity {


    EditText etDate, etTime, etNotes;
    Button btnConfirm;
    DatabaseReference appointmentsRef;
    String doctorId, patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booking);

        etDate = findViewById(R.id.etAppointmentDate);
        etTime = findViewById(R.id.etAppointmentTime);
        etNotes = findViewById(R.id.etNotes);
        btnConfirm = findViewById(R.id.btnConfirmBooking);

        doctorId = getIntent().getStringExtra("doctorId");
        patientId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments");

        btnConfirm.setOnClickListener(v -> {
            String id = appointmentsRef.push().getKey();
            Map<String, Object> appointment = new HashMap<>();
            appointment.put("id", id);
            appointment.put("doctorId", doctorId);
            appointment.put("patientId", patientId);
            appointment.put("date", etDate.getText().toString().trim());
            appointment.put("time", etTime.getText().toString().trim());
            appointment.put("notes", etNotes.getText().toString().trim());
            appointment.put("status", "Pending");

            appointmentsRef.child(id).setValue(appointment).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error booking appointment", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
