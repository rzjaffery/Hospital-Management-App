package com.rzjaffery.hms.ui.doctor;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.rzjaffery.hms.R;

public class DoctorDashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_doctor);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        topAppBar = findViewById(R.id.topAppBar);

        // Setup Toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, topAppBar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set user info in header
        View headerView = navigationView.getHeaderView(0);
        TextView tvName = headerView.findViewById(R.id.tvHeaderName);
        TextView tvRole = headerView.findViewById(R.id.tvHeaderRole);

        String userName = getIntent().getStringExtra("USER_NAME");
        tvName.setText(userName != null ? userName : "Doctor Name");
        tvRole.setText("Doctor");

        // Handle navigation clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_appointments) {
                    // Open appointments page
                } else if (id == R.id.nav_patients) {
                    // Open patient records
                } else if (item.getItemId() == R.id.nav_profile) {
                    // Open Profile
                } else if (id == R.id.nav_logout) {
                    finish();
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
