package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.others.CartManager;
import com.threehero.myhealth.others.MedicineAdapter;
import com.threehero.myhealth.others.MedicineDetails;
import com.threehero.myhealth.others.Medicine_list;

import java.util.ArrayList;
import java.util.List;

public class Medicine extends AppCompatActivity {

    private ArrayList<Medicine_list>  cartItems;
    List<Medicine_list> medicine_lists;
    private MedicineAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicine);
        ImageView cart;
        ImageView back;
        EditText searchBar;
        back = findViewById(R.id.back_btn);
        cart = findViewById(R.id.cart_btn);
        searchBar = findViewById(R.id.search_bar);
        cartItems = new ArrayList<>();

        CartManager.getInstance().loadCartItems(this);
        Log.d("Medicine", "onCreate: started");


        medicine_lists = new ArrayList<Medicine_list>();
        medicine_lists.add(new Medicine_list("Cardioplus", "Composition:\nChromium, Copper, Folic Acid, Maganese, Selenium, Vitamin A, Vitamin B12, Vitamin B6, Vitamin C, Vitamin E, Zinc", 135.0f, R.drawable.cardioplus, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Dolo 650", "Composition:\nParacetamol 650", 29.0f, R.drawable.dolo_650, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Flexon-MR", "Composition:\nIbuprofen (400mg) + Paracetamol (325mg) + Chlorzoxazone (250mg)", 22.68f, R.drawable.flexon_mr, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Indiclav", "Composition:\nAmoxycillin (500mg) + Clavulanic Acid (125mg)", 165.24f, R.drawable.indclav_bid, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Lopox", "Composition:\nLoperamide (2mg)", 9.70f, R.drawable.lopox, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Macbery-LS", "Composition:\nAmbroxol (30mg/5ml) + Levosalbutamol (1mg/5ml) + Guaifenesin (50mg/5ml)", 83.43f, R.drawable.macbery_ls, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Mega-CV", "Composition:\nAmoxycillin (500mg) + Clavulanic Acid (125mg)", 144.18f, R.drawable.mega_cv, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Meftal-Spas", "Composition:\nDicyclomine (10mg) + Mefenamic Acid (250mg)", 49.0f, R.drawable.meftal_spas, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Mobizox", "Composition:\nChlorzoxazone (500mg) + Diclofenac (50mg) + Paracetamol (325mg)", 200.88f, R.drawable.mobizox, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Nervijen-NP", "Composition:\nPregabalin (75mg) + Nortriptyline (10mg) + Methylcobalamin (1500mcg)", 145.8f, R.drawable.nervijen_np, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Nervijen-P", "Composition:\nBenfotiamine (7.5mg) + Folic Acid (0.75mg) + Methylcobalamin (750mcg) + Pregabalin (75mg) + Vitamin B6 (Pyridoxine) (1.5mg)", 206.55f, R.drawable.nervijen_p, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Nervijen-Plus", "Composition:\nMecobalamin J.P. + Alpha Lipoic Acid U.S.P. + Benfothiamine + Pyridoxine Hydrochloride (B6) I.P. + Nicotinamide (B3) I.P. + Folic Acid I.P.", 180.0f, R.drawable.nervijen_plus, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Pacimol", "Composition:\nParacetamol 100mg/ml", 31.0f, R.drawable.pacimol, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Pacimol-MF", "Composition:\nMefenamic Acid (100mg/5ml) + Paracetamol (250mg/5ml)", 59.13f, R.drawable.pacimol_mf, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Powergesic", "Composition:\nChlorzoxazone (250mg) + Diclofenac (50mg) + Paracetamol (325mg)", 119.88f, R.drawable.powergesic, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Powergesic-MR", "Composition:\nChlorzoxazone (500mg) + Diclofenac (50mg) + Paracetamol (325mg)", 149.04f, R.drawable.powergesic_mr, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Revelol-AM", "Composition:\nAmlodipine (5mg) + Metoprolol Succinate (47.5mg)", 191.25f, R.drawable.revelol_am, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Revelol-XL", "Composition:\nMetoprolol Succinate (47.5mg)", 84.37f, R.drawable.revelol_xl, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Solvin-Cold", "Composition:\nChlorpheniramine Maleate (2mg) + Paracetamol (500mg) + Phenylephrine (10mg)", 51.85f, R.drawable.solvin_cold, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Tel-Revelol", "Composition:\nTelmisartan (40mg) + Metoprolol Succinate (50mg)", 162.35f, R.drawable.tel_revelol, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Telvas-40", "Composition:\nTelmisartan (20mg)", 46.17f, R.drawable.telvas_40, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Zerodol-P", "Composition:\nAceclofenac (100mg) + Paracetamol (325mg)", 56.7f, R.drawable.zerodol_p, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Zerodol-SP", "Composition:\nAceclofenac (100mg) + Paracetamol (325mg) + Serratiopeptidase (15mg)", 106.25f, R.drawable.zerodol_sp, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("Zix-MR", "Composition:\nAceclofenac (100mg) + Thiocolchicoside (4mg)", 2098.17f, R.drawable.zix_mr, R.drawable.cart2));
        medicine_lists.add(new Medicine_list("ZixFlam", "Composition:\nTrypsin (50000AU) + Rutoside (100mg) + Bromelain (90mg) + Aceclofenac (100mg)", 176.75f, R.drawable.zixflam, R.drawable.cart2));


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medicine.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medicine.this,Cart.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicineAdapter(this, medicine_lists);
        recyclerView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showMedicineDetailsPopup(MedicineDetails medicine) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_medicine_detail, null);
        builder.setView(dialogView);

        TextView meddname = dialogView.findViewById(R.id.det_name);
        TextView meddcomp = dialogView.findViewById(R.id.det_comp);
        TextView meddprice = dialogView.findViewById(R.id.det_price);
        TextView productintro = dialogView.findViewById(R.id.product_intro);
        ImageView meddimg = dialogView.findViewById(R.id.det_img);
        TextView safetyAdvice = dialogView.findViewById(R.id.safety_advice);
        TextView sideEffects = dialogView.findViewById(R.id.side_effects);

        meddname.setText(medicine.getMedname());
        meddcomp.setText(medicine.getMedcomp());
        meddprice.setText(String.valueOf(medicine.getMedprice()));
        productintro.setText(medicine.getProductintro());
        safetyAdvice.setText(medicine.getSafety());
        sideEffects.setText(medicine.getEfffects());

        Log.d("MedicineDetails", "Image Name: " + medicine.getMedimg());
        int resId = getResources().getIdentifier(medicine.getMedimg(), "drawable", getPackageName());
        if (resId != 0) {
            meddimg.setImageResource(resId);
        } else {
            Log.e("MedicineDetails", "Image resource not found for: " + medicine.getMedimg());
            meddimg.setImageResource(R.drawable.back_icon);  // Fallback image
        }


        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}



