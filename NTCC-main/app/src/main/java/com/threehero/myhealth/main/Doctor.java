package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import com.threehero.myhealth.others.DoctorAdapter;
import com.threehero.myhealth.others.DoctorDet;
import com.threehero.myhealth.others.Doctor_list;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor);

        ImageView back = findViewById(R.id.back_btn);

        List<Doctor_list> doctorLists = new ArrayList<>();
        doctorLists.add(new Doctor_list("Dr. Dheeraj Setia", "Endodontist,\n" + "Implantologist,\n" + "Cosmetic/Aesthetic Dentist,\n" + "Dentist\n", 1500.0f, R.drawable.dheeraj_setia, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Satbir Singh", "Cardiologist,\n" + "General Physician,\n" + "Consultant Physician,\n" + "Interventional Cardiologist\n", 1000.0f, R.drawable.satbirsingh, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Manish Arya", "ENT/ Otorhinolaryngologist", 900.0f, R.drawable.manish, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Parul Katiyar", "Infertility Specialist\n" , 800.0f, R.drawable.parul, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. K.S. Walia", "General Physician", 1500.0f, R.drawable.kswalia, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Praveen Kumar", "Dermatologist,\n" + "Aesthetic Dermatologist,\n" + "Cosmetologist\n", 1800.0f, R.drawable.praveen, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Harit Chaturvedi", "Surgical Oncologist\n" , 1500.0f, R.drawable.harit, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. B. S. Solanki", "Nephrologist/Renal Specialist", 1400.0f, R.drawable.bssolanki, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Aishwarya Krishnamurthy", "Endocrinologist,\n" + "Diabetologist\n", 1500.0f, R.drawable.aishwarya, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Rahul Sharma", "Orthopedic surgeon,\n" + "Spine And Pain Specialist\n", 1400.0f, R.drawable.rahul, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Salsabeel Syed", "Radiologist", 1500.0f, R.drawable.salsabeel, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Varun Gogia", "Ophthalmologist/ Eye Surgeon", 1400.0f, R.drawable.varun, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Ajay Kumar", "Gastroenterologist", 1500.0f, R.drawable.ajay, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Dixit Kumar Thakur", "Pulmonologist", 1400.0f, R.drawable.dixit, R.drawable.book_icon));
        doctorLists.add(new Doctor_list("Dr. Kadam Nagpal", "Neurologist", 1500.0f, R.drawable.kadam, R.drawable.book_icon));



        back.setOnClickListener(v -> {
            Intent intent = new Intent(Doctor.this, Home.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DoctorAdapter adapter = new DoctorAdapter(this, doctorLists);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showMedicineDetailsPopup(DoctorDet det) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_doctor, null);
        builder.setView(dialogView);

        TextView Docname = dialogView.findViewById(R.id.doc_name);
        TextView Docdes = dialogView.findViewById(R.id.discription);
        ImageView Docimg = dialogView.findViewById(R.id.doc_img);

        Docname.setText(det.getName());
        Docdes.setText(det.getDes());
        Log.d("MedicineDetails", "Image Name: " + det.getDocimg());
        int resId = getResources().getIdentifier(det.getDocimg(), "drawable", getPackageName());
        if (resId != 0) {
            Docimg.setImageResource(resId);
        } else {
            Log.e("MedicineDetails", "Image resource not found for: " + det.getDocimg());
            Docimg.setImageResource(R.drawable.back_icon);  // Fallback image
        }


        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
