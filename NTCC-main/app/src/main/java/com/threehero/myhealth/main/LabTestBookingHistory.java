package com.threehero.myhealth.main;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.threehero.myhealth.databinding.ActivityLabTestBookingHistoryBinding;
import com.threehero.myhealth.R;

public class LabTestBookingHistory extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLabTestBookingHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the binding layout and set it as the content view
        binding = ActivityLabTestBookingHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the RecyclerView using binding
        RecyclerView bookingHistoryRecyclerView = binding.bookingHistoryRecyclerview;
        bookingHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the toolbar
        setSupportActionBar(binding.toolbar);

        // Set up NavController and AppBarConfiguration
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_lab_test_booking_history);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Set up the Floating Action Button (FAB) if you have it
        if (binding.fab != null) {
            binding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAnchorView(binding.fab)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_lab_test_booking_history);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
