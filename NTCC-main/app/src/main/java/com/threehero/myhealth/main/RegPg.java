package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.threehero.myhealth.R;

public class RegPg extends AppCompatActivity {

    // Declare UI elements
    private EditText firstNameEditText, userNameEditText, ageEditText, emailEditText, confpasswordEditText, passwordEditText;
    private Spinner genderSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_pg); // Assuming you have a layout file named reg_pg.xml

        // Initialize views
        firstNameEditText = findViewById(R.id.firstName);
        userNameEditText = findViewById(R.id.userName);
        ageEditText = findViewById(R.id.age);
        emailEditText = findViewById(R.id.email);
        confpasswordEditText = findViewById(R.id.confirmpassword);
        genderSpinner = findViewById(R.id.genderSelect);
        registerButton = findViewById(R.id.registerButton);
        passwordEditText = findViewById(R.id.password);

        // Set onClickListener for the register button
        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Retrieve user inputs
        String firstName = firstNameEditText.getText().toString().trim();
        String userName = userNameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String confpass = confpasswordEditText.getText().toString().trim();
        String gender = genderSpinner.getSelectedItem().toString();
        String password = passwordEditText.getText().toString().trim();

        // Validate inputs
        if (firstName.isEmpty()) {
            firstNameEditText.setError("First Name is required");
            firstNameEditText.requestFocus();
            return;
        }

        if (userName.isEmpty()) {
            userNameEditText.setError("Last Name is required");
            userNameEditText.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            ageEditText.setError("Age is required");
            ageEditText.requestFocus();
            return;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Valid email is required");
            emailEditText.requestFocus();
            return;
        }

        // Check if password fields are empty
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }

        if (confpass.isEmpty()) {
            confpasswordEditText.setError("Please confirm your password");
            confpasswordEditText.requestFocus();
            return;
        }

        // Check if passwords match
        if (!password.equals(confpass)) {
            confpasswordEditText.setError("Passwords do not match");
            confpasswordEditText.requestFocus();
            return;
        }

        // If all validations pass, proceed with registration (replace with your actual registration logic)
        Toast.makeText(RegPg.this, "Registered successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegPg.this, LoginPage.class); // Assuming you have a LoginPage activity
        startActivity(intent);
    }
}
