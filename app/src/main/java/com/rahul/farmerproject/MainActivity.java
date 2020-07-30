package com.rahul.farmerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void btnlogin(View view){
        Intent it=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(it);
    }
    public void btnRegFarmer(View view){
        Intent it1;
        it1 = new Intent(MainActivity.this,FarmerRegisterActivity.class);
        startActivity(it1);
    }
    public void btnRegBuyer(View view){
        Intent it2=new Intent(MainActivity.this,FarmerRegisterActivity.class);
        startActivity(it2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
