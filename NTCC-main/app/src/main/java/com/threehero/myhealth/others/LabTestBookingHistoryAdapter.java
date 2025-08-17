package com.threehero.myhealth.others;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;

import java.util.List;

public class LabTestBookingHistoryAdapter extends RecyclerView.Adapter<LabTestBookingHistoryAdapter.BookingViewHolder> {

    private List<LabTestBookingDetails> bookingHistory;

    public LabTestBookingHistoryAdapter(List<LabTestBookingDetails> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_test_booking_history_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        // Get the current booking item
        LabTestBookingDetails booking = bookingHistory.get(position);

        // Bind data to the views
        holder.testName.setText(booking.getTestName());
        holder.bookingDate.setText("Booked on: " + booking.getBookingDate());
        holder.address.setText("Address: " + booking.getAddress());
        holder.phoneNumber.setText("Phone: " + booking.getPhoneNumber());
        holder.price.setText(String.format("Price: ₹%.2f", booking.getTestPrice()));
    }

    @Override
    public int getItemCount() {
        return bookingHistory != null ? bookingHistory.size() : 0;
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView testName, bookingDate, address, phoneNumber, price;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            testName = itemView.findViewById(R.id.test_name_text);
            bookingDate = itemView.findViewById(R.id.booking_date_text);
            address = itemView.findViewById(R.id.address_text);
            phoneNumber = itemView.findViewById(R.id.phone_number_text);
            price = itemView.findViewById(R.id.price_text);
        }
    }
}
