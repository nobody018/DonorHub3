package com.example.donorhub.Helperfile;

public class FeaturedHelperClass {

    private String ngo_name, description, phoneNo;
    private int ngo_image;

    public FeaturedHelperClass() {
    }

    public FeaturedHelperClass(String ngo_name, String description, String phoneNo, int ngo_image) {
        this.ngo_name = ngo_name;
        this.description = description;
        this.phoneNo = phoneNo;
        this.ngo_image = ngo_image;
    }

    public int getNgo_image() {
        return ngo_image;
    }

    public void setNgo_image(int ngo_image) {
        this.ngo_image = ngo_image;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
