package com.threehero.myhealth.others;

import java.io.Serializable;

public class LabTestBookingDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private String testName;
    private float testPrice;
    private String bookingDate;
    private String address;
    private String phoneNumber;

    // Default constructor
    public LabTestBookingDetails() {}

    // Parameterized constructor
    public LabTestBookingDetails(String testName, float testPrice, String bookingDate, String address, String phoneNumber) {
        this.testName = testName;
        this.testPrice = testPrice;
        this.bookingDate = bookingDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public float getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(float testPrice) {
        this.testPrice = testPrice;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "LabTestBookingDetails{" +
                "testName='" + testName + '\'' +
                ", testPrice=" + testPrice +
                ", bookingDate='" + bookingDate + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
