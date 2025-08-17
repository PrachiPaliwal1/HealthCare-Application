package com.threehero.myhealth.others;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONDoctor {
    public static List<DoctorDet> loadJSONFromAsset(Context context, String filename) {
        List<DoctorDet> dets = new ArrayList<>();
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

                DoctorDet det = new DoctorDet();
                det.setName(obj.getString("medname"));
                Log.d("JSONHelper", "name: " + obj.getString("name"));
                det.setDocimg(obj.getString("imageRes"));
                Log.d("JSONHelper", "imageRes: " + obj.getString("imageRes"));
                det.setDes(obj.getString("des"));
                Log.d("JSONHelper", "des: " + obj.getString("des"));
                dets.add(det);
            }

        } catch (IOException | org.json.JSONException ex) {
            ex.printStackTrace();
            Log.e("JSONHelper", "Error loading JSON from asset", ex);
        }

        Log.d("JSONHelper", "Total medicines parsed: " + dets.size());

        return dets;
    }
}
