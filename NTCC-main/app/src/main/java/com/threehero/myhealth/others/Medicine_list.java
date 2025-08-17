package com.threehero.myhealth.others;

public class Medicine_list {
    String name;
    String comp;
    float price;
    int medimg;
    int cartimg;

    public Medicine_list(String name, String comp, float price, int medimg, int cartimg) {
        this.name = name;
        this.comp = comp;
        this.price = price;
        this.medimg = medimg;
        this.cartimg = cartimg;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMedimg() {
        return medimg;
    }

    public void setMedimg(int medimg) {
        this.medimg = medimg;
    }


    public int getCartimg() {
        return cartimg;
    }
}


