package com.rzjaffery.hms.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.rzjaffery.hms.R;

public class PatientDashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_patient);

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
        tvName.setText(userName != null ? userName : "Patient Name");
        tvRole.setText("Patient");

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_appointments) {
                // Show appointment
            } else if (item.getItemId() == R.id.nav_book_appointments) {
                // Book new appointments
            } else if (item.getItemId() == R.id.nav_prescriptions) {
                // Show prescription
            } else if (item.getItemId() == R.id.nav_profile) {
                // Show Profile
            } else if (item.getItemId() == R.id.nav_logout) {
                finish();
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search doctors...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(PatientDashboardActivity.this, DoctorSearchActivity.class);
                intent.putExtra("searchQuery", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

}
