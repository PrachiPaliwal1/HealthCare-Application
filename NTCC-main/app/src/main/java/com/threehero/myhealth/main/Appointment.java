package com.threehero.myhealth.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.threehero.myhealth.R;
import com.threehero.myhealth.others.Doctor_list;

public class Appointment extends AppCompatActivity {

    ImageView backbtn, homebtn, doctorImage;
    TextView doctorName, doctorSpeciality, doctorFee;
    RadioGroup timeSlotGroup;
    Button confirmBtn, clearBtn;

    Doctor_list selectedDoctor; // Store the selected doctor here

    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppointmentPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        backbtn = findViewById(R.id.back_btn);
        homebtn = findViewById(R.id.home_btn);
        doctorImage = findViewById(R.id.doctor_image);
        doctorName = findViewById(R.id.doctor_name);
        doctorSpeciality = findViewById(R.id.doctor_speciality);
        doctorFee = findViewById(R.id.doctor_fee);
        timeSlotGroup = findViewById(R.id.time_slots_group);
        confirmBtn = findViewById(R.id.confirm_btn);
        clearBtn = findViewById(R.id.clear_btn);

        // Load selected doctor details from intent
        loadDoctorDetails();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to previous activity
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Appointment.this, Home.class);
                startActivity(intent);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTimeSlotId = timeSlotGroup.getCheckedRadioButtonId();
                if (selectedTimeSlotId != -1) {
                    RadioButton selectedTimeSlot = findViewById(selectedTimeSlotId);
                    saveAppointmentDetails(selectedTimeSlot.getText().toString());
                    Toast.makeText(Appointment.this, "Appointment Confirmed", Toast.LENGTH_SHORT).show();

                    timeSlotGroup.setEnabled(false);
                    confirmBtn.setVisibility(View.GONE);
                    clearBtn.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(Appointment.this, "Please select a time slot", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAppointmentDetails();
                timeSlotGroup.clearCheck();
                timeSlotGroup.setEnabled(true);
                confirmBtn.setVisibility(View.VISIBLE);
                clearBtn.setVisibility(View.GONE);
                doctorImage.setImageResource(android.R.color.transparent);
                doctorName.setText("");
                doctorSpeciality.setText("");
                doctorFee.setText("");

                // Navigate to ConfirmedAppointments
                Intent intent = new Intent(Appointment.this, ConfirmedAppointments.class);
                startActivity(intent);
            }
        });
    }

    private void loadDoctorDetails() {
        Intent intent = getIntent();
        selectedDoctor = (Doctor_list) intent.getSerializableExtra("selectedDoctor");

        if (selectedDoctor != null) {
            doctorName.setText(selectedDoctor.getName());
            doctorSpeciality.setText(selectedDoctor.getSpecialty());
            doctorFee.setText(String.format("Fee: $%.2f", selectedDoctor.getFee()));
            doctorImage.setImageResource(selectedDoctor.getImageResId()); // Assuming imageResourceId is available in Doctor_list class
        }
    }

    private void saveAppointmentDetails(String timeSlot) {
        SharedPreferences.Editor editor = sharedPreferences.edit();editor.putBoolean("appointmentConfirmed", true);
        editor.putString("doctorName", doctorName.getText().toString());
        editor.putString("doctorSpeciality", doctorSpeciality.getText().toString());

        // Fix for NumberFormatException:
        String feeString = doctorFee.getText().toString().replace("Fee: $", ""); // Remove "Fee: $"
        float fee = Float.parseFloat(feeString);
        editor.putFloat("doctorFee", fee);

        editor.putInt("doctorImageResourceId", selectedDoctor.getImageResId());
        editor.putString("timeSlot", timeSlot);
        editor.apply();
    }

    private void clearAppointmentDetails() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
