package com.threehero.myhealth.others;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.threehero.myhealth.MyApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Sharedprefmed {
    private static final String PREF_NAME = "cart_prefs";
    private static final String CART_KEY = "cart_items";
    private static final String CART_KEY_LAB = "cart_items_lab";
    private static final String ORDER_KEY = "medicine_orders";
    private static final String BOOKING_HISTORY_KEY = "lab_test_bookings";

    public static void saveCartItems(List<Medicine_list> cartItems) {
        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public static void saveCartLabItems(List<LabTests_list> cartLabItems) {
        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartLabItems);
        editor.putString(CART_KEY_LAB, json);
        editor.apply();
    }

    public static List<Medicine_list> getCartItems() {
        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_KEY, null);
        Type type = new TypeToken<ArrayList<Medicine_list>>() {
        }.getType();
        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public static List<LabTests_list> getCartLabItems() {
        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_KEY_LAB, null);
        Type type = new TypeToken<ArrayList<LabTests_list>>() {
        }.getType();
        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public static void addMedicineOrderItems(List<Medicine_list> orderItems) {
        List<List<Medicine_list>> allOrders = getMedicineOrders(); // Get existing orders
        allOrders.add(orderItems);
        saveMedicineOrders(allOrders);
    }

    public static void saveMedicineOrders(List<List<Medicine_list>> allOrders) {
        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(allOrders);
        editor.putString(ORDER_KEY, json);
        editor.apply();
    }

    public static List<List<Medicine_list>> getMedicineOrders() {
        SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(ORDER_KEY, null);
        Type type = new TypeToken<ArrayList<ArrayList<Medicine_list>>>() {
        }.getType();
        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public static void addLabTestBooking(LabTestBookingDetails bookingDetails) {
            List<LabTestBookingDetails> bookingHistory = getLabTestBookings();
            bookingHistory.add(bookingDetails);
            saveLabTestBookings(bookingHistory);
    }

    public static void saveLabTestBookings(List<LabTestBookingDetails> bookingHistory) {
            SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(bookingHistory);
            editor.putString(BOOKING_HISTORY_KEY, json);editor.apply();
    }

        public static List<LabTestBookingDetails> getLabTestBookings() {
            SharedPreferences prefs = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = prefs.getString(BOOKING_HISTORY_KEY, null);
            Type type = new TypeToken<ArrayList<LabTestBookingDetails>>() {}.getType();
            return json == null ? new ArrayList<>() : gson.fromJson(json, type);
        }
    }

