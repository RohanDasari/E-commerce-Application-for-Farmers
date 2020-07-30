package com.rahul.farmerproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FarmerDashboard extends AppCompatActivity {
    private Button Profile;
    private Button AddProduct;
    private Button MyOrders;
    private Button SignOut;

    public void profileclick(View view)
    {
        Toast.makeText(FarmerDashboard.this,"profile details", Toast.LENGTH_LONG).show();
        Intent ifd1=new Intent(FarmerDashboard.this ,ImagesActivity.class);
        startActivity(ifd1);
    }

    public void addproductclick(View view)
    {
        Toast.makeText(FarmerDashboard.this,"product details",Toast.LENGTH_LONG).show();
        Intent ifd2=new Intent(FarmerDashboard.this ,AddProducts.class);
        startActivity(ifd2);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void SignOutClick(View view){
        finishAffinity();
        Intent intent=new Intent(FarmerDashboard.this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_dashboard);
        
    }
}
