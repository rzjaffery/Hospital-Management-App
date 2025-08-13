package com.rzjaffery.hms.ui.admin;

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

public class AdminDashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        topAppBar = findViewById(R.id.topAppBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, topAppBar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        TextView tvName = headerView.findViewById(R.id.tvHeaderName);
        TextView tvRole = headerView.findViewById(R.id.tvHeaderRole);

        String userName = getIntent().getStringExtra("USER_NAME");
        tvName.setText(userName != null ? userName : "Admin Name");
        tvRole.setText("Admin");

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_manage_doctors) {
                // Manage doctors
            } else if (item.getItemId() == R.id.nav_manage_patients) {
                // Manage Patients
            } else if (item.getItemId() == R.id.nav_hospital_reports) {
                // Display Hospital Records
            } else if (item.getItemId() == R.id.nav_setting) {
                // Open Setting
            } else if (item.getItemId() == R.id.nav_logout) {
                finish();
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }
}
