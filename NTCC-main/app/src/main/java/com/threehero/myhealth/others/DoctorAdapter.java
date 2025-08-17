
package com.threehero.myhealth.others;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.main.Doctor;
import com.threehero.myhealth.main.Medicine;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context context;
    private List<Doctor_list> doctorLists;

    public DoctorAdapter(Context context, List<Doctor_list> doctorLists) {
        this.context = context;
        this.doctorLists = doctorLists;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor_list doctor = doctorLists.get(position);
        holder.nameTextView.setText(doctor.getName());
        holder.specialtyTextView.setText(doctor.getSpecialty());
        holder.feeTextView.setText(String.format("₹%.2f", doctor.getFee()));
        holder.imageView.setImageResource(doctor.getImageResId());
        holder.bookButton.setImageResource(doctor.getBookImageResId());

        holder.bookButton.setOnClickListener(v ->
                Toast.makeText(context, "Booking " + doctor.getName(), Toast.LENGTH_SHORT).show());

        holder.itemView.setOnClickListener(v -> {
            if (context instanceof Doctor) {
                List<DoctorDet> dets = JSONDoctor.loadJSONFromAsset(context, "doctors.json");
                
                if (dets != null && !dets.isEmpty()) {
                    DoctorDet detail = dets.stream()
                            .filter(det -> det.getName().equals(doctor.getName()))
                            .findFirst()
                            .orElse(null);

                    if (detail != null) {
                        ((Doctor) context).showMedicineDetailsPopup(detail);
                    } else {
                        Log.d("DoctorAdapter", "No details found for doctor: " + doctor.getName());
                    }
                } else {
                    Log.e("DoctorAdapter", "No medicines found or failed to load medicines from JSON");
                }
            } else {
                Log.e("DoctorAdapter", "Context is not an instance of Medicine");
            }
        });
    }
    
    

    @Override
    public int getItemCount() {
        return doctorLists.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView specialtyTextView;
        TextView feeTextView;
        ImageView imageView;
        ImageView bookButton;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.doc_name);
            specialtyTextView = itemView.findViewById(R.id.specialty);
            feeTextView = itemView.findViewById(R.id.fee);
            imageView = itemView.findViewById(R.id.doc_img);
            bookButton = itemView.findViewById(R.id.book_btn);
        }
    }
}
