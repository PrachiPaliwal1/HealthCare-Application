package com.threehero.myhealth.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.threehero.myhealth.R;

public class ConfirmedAppointments extends AppCompatActivity {

    ImageView backbtn, homebtn;
    TextView doctorName, doctorSpeciality, doctorFee, timeSlot;
    ImageView doctorImage;
    Button clearBtn;
    LinearLayout doctorInfoContainer;

    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppointmentPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_appointments);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        backbtn = findViewById(R.id.back_btn);
        homebtn = findViewById(R.id.home_btn);
        doctorImage = findViewById(R.id.doctor_image);
        doctorName = findViewById(R.id.doctor_name);
        doctorSpeciality = findViewById(R.id.doctor_speciality);
        doctorFee = findViewById(R.id.doctor_fee);
        timeSlot = findViewById(R.id.time_slot);
        clearBtn = findViewById(R.id.clear_btn);
        doctorInfoContainer = findViewById(R.id.doctor_info_container);

        loadDoctorInfo();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmedAppointments.this, Appointment.class);
                startActivity(intent);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmedAppointments.this, Home.class);
                startActivity(intent);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDoctorInfo();
                Intent intent = new Intent(ConfirmedAppointments.this, Appointment.class);
                startActivity(intent);
            }
        });
    }

    private void loadDoctorInfo() {
        boolean appointmentConfirmed = sharedPreferences.getBoolean("appointmentConfirmed", false);
        if (appointmentConfirmed) {
            String name = sharedPreferences.getString("doctorName", "");
            String speciality = sharedPreferences.getString("doctorSpeciality", "");
            float fee = sharedPreferences.getFloat("doctorFee", 0);
            int imageResourceId = sharedPreferences.getInt("doctorImageResourceId", 0);
            String timeSlotText = sharedPreferences.getString("timeSlot", "");

            doctorName.setText(name);
            doctorSpeciality.setText(speciality);
            doctorFee.setText(String.format("Fee: $%.2f", fee));
            doctorImage.setImageResource(imageResourceId);
            timeSlot.setText(timeSlotText);

            doctorInfoContainer.setVisibility(View.VISIBLE);
        } else {
            doctorInfoContainer.setVisibility(View.GONE);
        }
    }

    private void clearDoctorInfo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
