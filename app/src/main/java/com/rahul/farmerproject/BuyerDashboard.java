package com.rahul.farmerproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyerDashboard extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BuyerAdapter mAdapter;
    private ProgressBar circProgressBar;
    private DatabaseReference mDatabaseRef;
    private List<product> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);
       // lv=findViewById(R.id.list_view);
        circProgressBar=findViewById(R.id.progress_cir);
        mRecyclerView=findViewById(R.id.recycle_View);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads= new ArrayList<product>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("AllProduct");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    String Url=postSnapshot.child("imagrurl").getValue(String.class);
                    String ProdName=postSnapshot.child("name").getValue(String.class);
                    String price=postSnapshot.child("quantity").getValue(String.class);
                    String node=postSnapshot.child("node").getValue(String.class);
                    product upload=new product(ProdName,Url,price,node);
                    mUploads.add(upload);
                    circProgressBar.setVisibility(View.INVISIBLE);
                }

                //mAdapter=new ImageAdapter(BuyerDashboard.this,mUploads);
                mAdapter=new BuyerAdapter(BuyerDashboard.this,mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.SetOnItemClickListener(new BuyerAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        String node=mUploads.get(position).getNode().trim();
                        Intent it=new Intent(BuyerDashboard.this,Item.class);
                        it.putExtra("Node",node);
                        startActivity(it);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuyerDashboard.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                circProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}
