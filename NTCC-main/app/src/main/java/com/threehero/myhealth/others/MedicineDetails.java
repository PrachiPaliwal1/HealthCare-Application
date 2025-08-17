package com.threehero.myhealth.others;


public class MedicineDetails {
    private String medname;
    private String medcomp;
    private String productintro;
    private float medprice;
    private String medimg;
    private String safety;
    private String efffects;

    // Getters and setters
    public String getMedname() { return medname; }
    public void setMedname(String medname) { this.medname = medname; }

    public String getProductintro() {
        return productintro;
    }

    public void setProductintro(String productintro) {
        this.productintro = productintro;
    }

    public String getMedcomp() { return medcomp; }
    public void setMedcomp(String medcomp) { this.medcomp = medcomp; }



    public float getMedprice() { return medprice; }
    public void setMedprice(float medprice) { this.medprice = medprice; }

    public String getMedimg() { return medimg; }
    public void setMedimg(String medimg) { this.medimg = medimg; }

    public String getSafety() { return safety; }
    public void setSafety(String safety) { this.safety = safety; }

    public String getEfffects() { return efffects; }
    public void setEfffects(String efffects) { this.efffects = efffects; }
}
