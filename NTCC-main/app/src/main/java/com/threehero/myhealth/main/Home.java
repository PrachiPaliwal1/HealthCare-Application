package com.threehero.myhealth.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;
import com.threehero.myhealth.MyApplication;
import com.threehero.myhealth.R;

public class Home extends AppCompatActivity {
    ImageView cartb, medib, userImg, drb, art, test, logout, detail;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        medib = findViewById(R.id.medicine_img);
        drb = findViewById(R.id.dr_img);
        cartb = findViewById(R.id.cart_bhome);
        name = findViewById(R.id.hello_msg);
        userImg = findViewById(R.id.user_icon);
        art = findViewById(R.id.article_img);
        test = findViewById(R.id.labtest_img);
        detail = findViewById(R.id.orderdet_img);
        logout = findViewById(R.id.logout_png);

        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String firstName = prefs.getString("first_name", "User");
        String photoUrl = prefs.getString("photo_url", null);

        name.setText("Hello\n    " + firstName);

        if (photoUrl != null) {
            Picasso.get()
                    .load(photoUrl)
                    .placeholder(R.drawable.circular_img)
                    .into(userImg);
        }

        cartb.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Cart.class);
            startActivity(intent);
        });

        art.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ArticlesActivity.class);
            startActivity(intent);
        });

        drb.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Doctor.class);
            startActivity(intent);
        });

        medib.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Medicine.class);
            startActivity(intent);
        });

        test.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, LabTests.class);
            startActivity(intent);
            });



        logout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(Home.this, LoginPage.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
