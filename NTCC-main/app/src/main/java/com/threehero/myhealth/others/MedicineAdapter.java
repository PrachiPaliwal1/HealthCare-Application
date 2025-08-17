package com.threehero.myhealth.others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.main.Medicine;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.Viewholder> {

    Context context;
    List<Medicine_list> medicineLists;
    List<Medicine_list> medicineListsFull; //for backup


    public MedicineAdapter(Context context, List<Medicine_list> medicineLists) {
        this.context = context;
        this.medicineLists = new ArrayList<>(medicineLists);
        this.medicineListsFull = new ArrayList<>(medicineLists);

    }

    @NonNull

    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list, parent, false);
        return new Viewholder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Medicine_list currentMedicine = medicineLists.get(position);
        holder.priceofmedi.setText(String.format(Locale.getDefault(), "%.2f", currentMedicine.getPrice()));
        holder.name_medi.setText(currentMedicine.getName());
        holder.name_comp.setText(currentMedicine.getComp());
        holder.img_medi.setImageResource(currentMedicine.getMedimg());
        holder.img_cart.setImageResource(currentMedicine.getCartimg());
        holder.img_cart.setOnClickListener(v -> {
            List<Medicine_list> cartItems = Sharedprefmed.getCartItems();
            cartItems.add(currentMedicine);
            Sharedprefmed.saveCartItems(cartItems);
            Toast.makeText(context, currentMedicine.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {
            if (context instanceof Medicine) {
                List<MedicineDetails> medicines = JSONHelper.loadJSONFromAsset(context, "medins.json");
                Log.d("MedicineAdapter", "Loaded medicines: " + medicines.size());

                if (medicines != null && !medicines.isEmpty()) {
                    MedicineDetails details = medicines.stream()
                            .filter(medicine -> medicine.getMedname().equals(currentMedicine.getName()))
                            .findFirst()
                            .orElse(null);

                    if (details != null) {
                        ((Medicine) context).showMedicineDetailsPopup(details);
                    } else {
                        Log.d("MedicineAdapter", "No details found for medicine: " + currentMedicine.getName());
                    }
                } else {
                    Log.e("MedicineAdapter", "No medicines found or failed to load medicines from JSON");
                }
            } else {
                Log.e("MedicineAdapter", "Context is not an instance of Medicine");
            }
        });


    }


    @Override
    public int getItemCount() {
        return medicineLists.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img_medi, img_cart;
        TextView name_medi, name_comp, priceofmedi;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_medi = itemView.findViewById(R.id.medi_img);
            img_cart = itemView.findViewById(R.id.addcart_btn);
            name_medi = itemView.findViewById(R.id.medi_name);
            name_comp = itemView.findViewById(R.id.salt_comp);
            priceofmedi = itemView.findViewById(R.id.price);

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void filter(String text) {
        List<Medicine_list> filteredlist = new ArrayList<>();
        if (text.isEmpty()) {
            filteredlist.addAll(medicineListsFull);
        } else {

            for (Medicine_list item : medicineListsFull) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredlist.add(item);
                }

            }

        }
        medicineLists.clear();
        medicineLists.addAll(filteredlist);
        notifyDataSetChanged();
    }





}
