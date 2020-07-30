package com.rahul.farmerproject;

public class product {
    private String name,imagrurl;
    private String quantity,node;

    public product(String name, String imagrurl, String quantity, String node) {
        this.name = name;
        this.imagrurl = imagrurl;
        this.quantity = quantity;
        this.node = node;

    }

    public String getName() {
        return name;
    }

    public String getImagrurl() {
        return imagrurl;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getNode() {
        return node;
    }
}
