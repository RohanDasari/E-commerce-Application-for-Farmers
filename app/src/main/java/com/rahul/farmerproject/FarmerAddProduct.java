package com.rahul.farmerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;

public class FarmerAddProduct extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_add_product);
        iv=findViewById(R.id.imageView3);
    }
}
