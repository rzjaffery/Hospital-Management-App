package com.rzjaffery.hms.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rzjaffery.hms.R;
import com.rzjaffery.hms.data.model.Doctor;
import com.rzjaffery.hms.utils.DoctorDetailsActivity;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context context;
    private List<Doctor> doctorList;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.tvDoctorName.setText(doctor.getName());
        holder.tvSpecialization.setText("Specialization: " + doctor.getSpecialization());
        holder.tvTimings.setText("Timings: " + doctor.getTimings());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DoctorDetailsActivity.class);
            intent.putExtra("doctorId", doctor.getId()); // Pass doctor ID to details page
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoctorName, tvSpecialization, tvTimings;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvSpecialization = itemView.findViewById(R.id.tvSpecialization);
            tvTimings = itemView.findViewById(R.id.tvTimings);
        }
    }
}
