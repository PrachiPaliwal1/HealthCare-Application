package com.threehero.myhealth.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.threehero.myhealth.R;
import com.threehero.myhealth.others.LabTests_list;

public class LabTestBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_booking);

        ImageView back = findViewById(R.id.back_btn);
        ImageView cart = findViewById(R.id.cart_btn);
        Button confirmButton = findViewById(R.id.book_test_button);

        TextView testNameView = findViewById(R.id.selected_test_name);
        TextView testPriceView = findViewById(R.id.selected_test_price);
        CalendarView calendarView = findViewById(R.id.calendarView);
        EditText addressInput = findViewById(R.id.address_input);
        EditText phoneInput = findViewById(R.id.phone_input);

        back.setOnClickListener(v -> {
            // Go back to LabTests activity
            Intent intent = new Intent(LabTestBooking.this, LabTests.class);
            startActivity(intent);
            finish(); // Optional: Finish the current activity
        });

        cart.setOnClickListener(v -> {
            Intent intent = new Intent(LabTestBooking.this, Cart.class);
            startActivity(intent);
        });

        confirmButton.setOnClickListener(v -> showConfirmationDialog());

        LabTests_list selectedLabTest = (LabTests_list) getIntent().getSerializableExtra("SELECTED_LAB_TEST");
        if (selectedLabTest != null) {
            testNameView.setText(selectedLabTest.getName());
            testPriceView.setText(String.format("₹%.2f", selectedLabTest.getTestprice()));
        }
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Booking");
        builder.setMessage("Are you sure you want to confirm this lab test booking?");
        builder.setPositiveButton("Confirm", (dialog, which) -> {
            // Handle the booking confirmation logic here
            // For example, you might want to send the data to a server or save it in local storage
            // You can use the addressInput, phoneInput, and calendarView to get the additional details
            String address = ((EditText) findViewById(R.id.address_input)).getText().toString();
            String phone = ((EditText) findViewById(R.id.phone_input)).getText().toString();
            long selectedDate = ((CalendarView) findViewById(R.id.calendarView)).getDate();

            // Example: Start a new activity or save the booking data
            Intent intent = new Intent(LabTestBooking.this, LabTestBookingHistory.class);

            intent.putExtra("DATE", selectedDate);
            startActivity(intent);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
