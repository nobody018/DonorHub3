package com.example.donorhub.database;

public class RequestHelperClass {
    private String ngo_name, description, purpose, category;
    private String ngo_image;

    public RequestHelperClass() {
        this.ngo_name = ngo_name;
        this.description = description;
        this.purpose = purpose;
        this.category = category;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNgo_image() {
        return ngo_image;
    }

    public void setNgo_image(String ngo_image) {
        this.ngo_image = ngo_image;
    }
}
