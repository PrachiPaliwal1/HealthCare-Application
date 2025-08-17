package com.threehero.myhealth.others;

import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONHelper {

    public static List<MedicineDetails> loadJSONFromAsset(Context context, String filename) {
        List<MedicineDetails> medicines = new ArrayList<>();
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                MedicineDetails medicine = new MedicineDetails();
                medicine.setMedname(obj.getString("medname"));
                Log.d("JSONHelper", "medname: " + obj.getString("medname"));
                medicine.setMedcomp(obj.getString("medcomp"));
                Log.d("JSONHelper", "medcomp: " + obj.getString("medcomp"));
                medicine.setMedprice((float) obj.getDouble("medprice"));
                Log.d("JSONHelper", "medprice: " + obj.getDouble("medprice"));
                medicine.setProductintro(obj.getString("productintro"));
                Log.d("JSONHelper", "productintro: " + obj.getString("productintro"));
                medicine.setMedimg(obj.getString("medimg"));
                Log.d("JSONHelper", "medimg: " + obj.getString("medimg"));
                medicine.setSafety(obj.getString("safety"));
                Log.d("JSONHelper", "safety: " + obj.getString("safety"));
                medicine.setEfffects(obj.getString("efffects"));
                Log.d("JSONHelper", "efffects: " + obj.getString("efffects"));

                medicines.add(medicine);
                Log.d("JSONHelper", "Parsed medicine: " + medicine.getMedname());
            }

        } catch (IOException | org.json.JSONException ex) {
            ex.printStackTrace();
            Log.e("JSONHelper", "Error loading JSON from asset", ex);
        }

        Log.d("JSONHelper", "Total medicines parsed: " + medicines.size());

        return medicines;
    }
}
