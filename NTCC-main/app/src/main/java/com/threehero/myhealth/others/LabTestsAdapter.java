package com.threehero.myhealth.others;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.main.LabTestBooking;

import java.util.ArrayList;
import java.util.List;

public class LabTestsAdapter extends RecyclerView.Adapter<LabTestsAdapter.LabTestsViewHolder> {

    private Context context;
    private List<LabTests_list> labTestsList;
    private List<LabTests_list> labTestsListFull; // To store the original list
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(LabTests_list labTest);
    }

    public LabTestsAdapter(Context context, List<LabTests_list> labTestsList, OnItemClickListener listener) {
        this.context = context;
        this.labTestsList = labTestsList;
        this.labTestsListFull = new ArrayList<>(labTestsList); // Initialize the full list
        this.listener = listener;
    }

    @NonNull
    @Override
    public LabTestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lab_test, parent, false);
        return new LabTestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabTestsViewHolder holder, int position) {
        final LabTests_list labTest = labTestsList.get(position);
        holder.testName.setText(labTest.getName());
        holder.testDescription.setText(labTest.getDescription());
        holder.testPrice.setText(String.format("₹%.2f", labTest.getTestprice()));
        holder.cartImage.setImageResource(labTest.getCartimg());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(labTest);
            }
            Toast.makeText(context, labTest.getName() + " is selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LabTestBooking.class);
            intent.putExtra("SELECTED_LAB_TEST", labTest);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return labTestsList.size();
    }

    public void filter(String text) {
        labTestsList.clear();
        if (text.isEmpty()) {
            labTestsList.addAll(labTestsListFull);
        } else {
            String lowerCaseText = text.toLowerCase();
            for (LabTests_list item : labTestsListFull) {
                if (item.getName().toLowerCase().contains(lowerCaseText)) {
                    labTestsList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class LabTestsViewHolder extends RecyclerView.ViewHolder {
        TextView testName, testDescription, testPrice;
        ImageView cartImage;

        public LabTestsViewHolder(@NonNull View itemView) {
            super(itemView);
            testName = itemView.findViewById(R.id.lab_test_name);
            testDescription = itemView.findViewById(R.id.lab_test_description);
            testPrice = itemView.findViewById(R.id.lab_test_price);
            cartImage = itemView.findViewById(R.id.cartImage);
        }
    }
}
