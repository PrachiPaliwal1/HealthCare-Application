package com.threehero.myhealth.others;

public class Doctor_list {
    private String name;
    private String specialty;
    private float fee;
    private int imageResId;
    private int bookImageResId;

    public Doctor_list(String name, String specialty, float fee, int imageResId, int bookImageResId) {
        this.name = name;
        this.specialty = specialty;
        this.fee = fee;
        this.imageResId = imageResId;
        this.bookImageResId = bookImageResId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public float getFee() {
        return fee;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getBookImageResId() {
        return bookImageResId;
    }
}
