package com.rahul.farmerproject;

public class uploads {
    private String name,imagrurl;
    String quantity;
    public uploads(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagrurl() {
        return imagrurl;
    }

    public void setImagrurl(String imagrurl) {
        this.imagrurl = imagrurl;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public uploads(String name, String imagrurl, String quantity) {
        this.name = name;
        this.imagrurl = imagrurl;
        this.quantity = quantity;
    }

}
