package com.rzjaffery.hms.ui.doctor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rzjaffery.hms.R;
import com.rzjaffery.hms.data.model.Doctor;
import com.rzjaffery.hms.utils.DoctorAdapter;
import com.rzjaffery.hms.utils.DoctorDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class DoctorSearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DoctorAdapter adapter;
    List<Doctor> doctorList = new ArrayList<>();
    DatabaseReference doctorsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recyclerView = findViewById(R.id.recyclerDoctors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DoctorAdapter(this, doctorList);
        recyclerView.setAdapter(adapter);

        String searchQuery = getIntent().getStringExtra("searchQuery");

        doctorsRef = FirebaseDatabase.getInstance().getReference("users");
        doctorsRef.orderByChild("role").equalTo("Doctor")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        doctorList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Doctor doctor = ds.getValue(Doctor.class);
                            if (doctor != null) {
                                assert searchQuery != null;
                                if (doctor.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                                        doctor.getSpecialization().toLowerCase().contains(searchQuery.toLowerCase())) {
                                    doctorList.add(doctor);
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });
    }

    private void openDoctorDetails(Doctor doctor) {
        Intent intent = new Intent(this, DoctorDetailsActivity.class);
        intent.putExtra("doctorId", doctor.getId());
        startActivity(intent);
    }
}
