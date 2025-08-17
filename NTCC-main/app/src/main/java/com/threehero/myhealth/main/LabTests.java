package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.others.LabTestsAdapter;
import com.threehero.myhealth.others.LabTests_list;

import java.util.ArrayList;
import java.util.List;

public class LabTests extends AppCompatActivity {

    private ArrayList<LabTests_list> cartLabItems;
    private List<LabTests_list> labTestsList;
    private LabTestsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_tests);

        // Initialize CartManager and log
        Log.d("LabTests", "onCreate: started");

        ImageView back = findViewById(R.id.back_btn);
        ImageView cart = findViewById(R.id.cart_btn);
        EditText searchBar = findViewById(R.id.search_bar);
        cartLabItems = new ArrayList<>();

        labTestsList = new ArrayList<>();
        labTestsList.add(new LabTests_list("Complete Blood Count (CBC)", "Checks for various blood components", 350.0f, R.drawable.cart2));
        labTestsList.add(new LabTests_list("Lipid Panel", "Measures cholesterol and triglyceride levels", 675.0f, R.drawable.cart2));
        labTestsList.add(new LabTests_list("HbA1c", "Measures average sugar level in blood", 275.0f, R.drawable.cart2));
        labTestsList.add(new LabTests_list("Allergy Screen", "Checks for allergies", 2550.0f, R.drawable.cart2));

        recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LabTestsAdapter(this, labTestsList, labTest -> addToCart(labTest));
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(LabTests.this, Home.class);
            startActivity(intent);
            finish();
        });

        cart.setOnClickListener(v -> {
            Intent intent = new Intent(LabTests.this, Cart.class);
            startActivity(intent);
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addToCart(LabTests_list labTest) {
        cartLabItems.add(labTest);
        // Optionally, save to SharedPreferences or notify Cart Manager
    }
}
