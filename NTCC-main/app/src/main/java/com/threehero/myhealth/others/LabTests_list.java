package com.threehero.myhealth.others;

import java.io.Serializable;

public class LabTests_list implements Serializable {
    private String name;
    private String description;
    private float price;
    private int cartimg;

    public LabTests_list(String name, String description, float price, int cartimg) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.cartimg = cartimg;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public float getTestprice() {
        return price;
    }
    public void setTestprice(float price) {
        this.price = price;
    }

    public int getCartimg() {
        return cartimg;
    }
    public void setCartimg(int cartimg) {
        this.cartimg = cartimg;
    }
}
