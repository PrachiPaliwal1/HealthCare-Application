package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.others.CartAdapter;
import com.threehero.myhealth.others.Medicine_list;
import com.threehero.myhealth.others.Sharedprefmed;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    private CartAdapter cartAdapter;
    ImageView backbtn,emptyCartImg,homebtn;
    TextView emptyMsg;
    Button buybtn, clearbtn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        homebtn = findViewById(R.id.home);
        buybtn = findViewById(R.id.buy_btn);
        backbtn = findViewById(R.id.back_btn);
        emptyCartImg = findViewById(R.id.empty_cartimg);
        emptyMsg = findViewById(R.id.empty_msg);
        recyclerView = findViewById(R.id.cart_rview);
        clearbtn = findViewById(R.id.clear_btn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Medicine.class);
                startActivity(intent);
            }
        });
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Medicine_list> cartItems = Sharedprefmed.getCartItems();
                if (!cartItems.isEmpty()) {
                    // Add items to order details
                    Sharedprefmed.addMedicineOrderItems(cartItems);
                    Toast.makeText(Cart.this, "Medicine Bought successfully, Check Order Details", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Cart.this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Home.class);
                startActivity(intent);
            }
        });
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sharedprefmed.saveCartItems(new ArrayList<>());
                showEmptyCart();
            }
        });


        List<Medicine_list> cartItems = Sharedprefmed.getCartItems();
        if (cartItems.isEmpty()) {
            emptyCartImg.setVisibility(View.VISIBLE);
            emptyMsg.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            buybtn.setVisibility(View.GONE);
            clearbtn.setVisibility(View.GONE);
        } else {
            emptyCartImg.setVisibility(View.GONE);
            emptyMsg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            buybtn.setVisibility(View.VISIBLE);
            clearbtn.setVisibility(View.VISIBLE);

            cartAdapter = new CartAdapter(this, cartItems);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(cartAdapter);
        }
    }

    private void showEmptyCart() {
        recyclerView.setVisibility(View.GONE);
        buybtn.setVisibility(View.GONE);
        clearbtn.setVisibility(View.GONE);
        emptyCartImg.setVisibility(View.VISIBLE);
        emptyMsg.setVisibility(View.VISIBLE);
    }

}
