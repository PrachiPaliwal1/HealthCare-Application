package com.threehero.myhealth.main;

import static com.threehero.myhealth.R.id.textView2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;
import com.threehero.myhealth.R;

public class Profile extends AppCompatActivity {

    private TextView textUserName, textUserEmail, textUserPhone;
    private ImageView profileImage; // Optional
    private View nameTextView = findViewById(R.id.userName);
    private View emailTextView = findViewById(R.id.email);
    private View phoneTextView = findViewById(R.id.phoneTextView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        phoneTextView = findViewById(R.id.phoneTextView);
        // Find other TextViews

        // Load user details from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String name = prefs.getString("first_name", "") + " " + prefs.getString("last_name", "");
        String email = prefs.getString("email", "");
        String phone = prefs.getString("phone", "");

        // Set user details to TextViews
        nameTextView.setTextDirection(Integer.parseInt("Name: " + name));
        emailTextView.setTextDirection(Integer.parseInt("Email: " + email));
        phoneTextView.setTextDirection(Integer.parseInt("Phone: " + phone));
    }
}
