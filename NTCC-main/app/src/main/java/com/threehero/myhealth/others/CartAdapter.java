package com.threehero.myhealth.others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<Medicine_list> cartItems;

    public CartAdapter(Context context, List<Medicine_list> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Medicine_list currentMedicine = cartItems.get(position);
        holder.name_medi.setText(currentMedicine.getName());
        holder.name_comp.setText(currentMedicine.getComp());
        holder.priceofmedi.setText(String.format(Locale.getDefault(), "%.2f", currentMedicine.getPrice()));
        holder.medicineimg.setImageResource(currentMedicine.getMedimg());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name_medi, name_comp, priceofmedi;
        ImageView medicineimg;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name_medi = itemView.findViewById(R.id.medi_name);
            name_comp = itemView.findViewById(R.id.salt_comp);
            priceofmedi = itemView.findViewById(R.id.price);
            medicineimg = itemView.findViewById(R.id.medimage);
        }
    }
}
