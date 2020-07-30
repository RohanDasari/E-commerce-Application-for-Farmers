package com.rahul.farmerproject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Node;

public class Item extends AppCompatActivity {
    private TextView textView,name,email,phone,Node;
    private String F_name, L_name,EM,ph;
    private DatabaseReference FarmerRef;
    private Farmer farmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent=getIntent();
        final String node=intent.getStringExtra("Node");
        textView=findViewById(R.id.key);
        textView.setText(node);
        //Node=findViewById(R.id.textView4);
        name=findViewById(R.id.textView4);
        email=findViewById(R.id.EMAIL);
        phone=findViewById(R.id.PHONE);
        String path="Farmer/"+node;
        FarmerRef=FirebaseDatabase.getInstance().getReference(path);
        FarmerRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               F_name=dataSnapshot.child("Fname").getValue(String.class)+" "+dataSnapshot.child("Lname").getValue(String.class);
               EM=dataSnapshot.child("Email").getValue(String.class);
               ph=dataSnapshot.child("Phone").getValue(String.class);
               //Node.setText(F_name);
               name.setText(F_name);
               email.setText(EM);
               phone.setText(ph);
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
        });
    }
}
