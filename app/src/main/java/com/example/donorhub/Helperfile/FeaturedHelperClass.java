package com.example.donorhub.Helperfile;

public class FeaturedHelperClass {

    int image;
    String ngo_name, description;

    public FeaturedHelperClass(int image, String ngo_name, String description) {
        this.image = image;
        this.ngo_name = ngo_name;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public String getDescription() {
        return description;
    }
}
