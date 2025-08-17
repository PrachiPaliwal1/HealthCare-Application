package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.threehero.myhealth.R;

public class LoginPage extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Login");
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressBar);
        authProfile = FirebaseAuth.getInstance();

        /*Button buttonForgotPassword = findViewById(R.id.buttonForgotPassword);
        buttonForgotPassword.setOnClickListener(view -> {
            Toast.makeText(login.this, "You can reset your password now.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(login.this, forgotpwd.class));
        });*/

        ImageView imageViewShowHidePassword = findViewById(R.id.imageViewShowHidePassword);
        imageViewShowHidePassword.setImageResource(R.drawable.ic_hide_pwd);
        imageViewShowHidePassword.setOnClickListener(view -> {
            if (editTextPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                imageViewShowHidePassword.setImageResource(R.drawable.ic_hide_pwd);
            } else {
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                imageViewShowHidePassword.setImageResource(R.drawable.ic_show_pwd);
            }
        });

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(view -> {
            String textEmail = editTextEmail.getText().toString();
            String textPassword = editTextPassword.getText().toString();

            if (TextUtils.isEmpty(textEmail)) {
                Toast.makeText(LoginPage.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                editTextEmail.setError("Email is required");
                editTextEmail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                editTextEmail.setError("Correct email is required");
                editTextEmail.requestFocus();
            } else if (TextUtils.isEmpty(textPassword)) {
                Toast.makeText(LoginPage.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                editTextPassword.setError("Password is required");
                editTextPassword.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                loginUser(textEmail, textPassword);
            }
        });
    }

    private void loginUser(String email, String password) {
        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginPage.this, "User Logged In", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    throw task.getException();
                } catch (FirebaseAuthInvalidUserException e) {
                    editTextEmail.setError("User is not registered");
                    editTextEmail.requestFocus();
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    editTextEmail.setError("Invalid credentials.");
                    editTextEmail.requestFocus();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
            progressBar.setVisibility(View.GONE);
        });
    }
}
