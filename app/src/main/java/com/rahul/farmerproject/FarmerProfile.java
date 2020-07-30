package com.rahul.farmerproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class FarmerProfile extends AppCompatActivity {
    private ListView lv;
    private DatabaseReference ref;
    private ArrayList<String> arr=new ArrayList<>();
    private ArrayList<Integer>  Images=new ArrayList<Integer>();
    private ArrayAdapter<String> products;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_profile);
        lv=findViewById(R.id.listView);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String No=firebaseUser.getPhoneNumber();
        String path="Farmer/"+No+"/Products";
        ref=FirebaseDatabase.getInstance().getReference(path);
        products=new ArrayAdapter<String>(FarmerProfile.this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(products);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //String value=dataSnapshot.getValue(String.class);
                String Key=dataSnapshot.getKey();

                arr.add(Key);
                products.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                products.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                products.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                products.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                products.notifyDataSetChanged();
            }
        });


    }
}
