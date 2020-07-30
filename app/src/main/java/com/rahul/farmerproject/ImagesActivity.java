package com.rahul.farmerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {
    String node="";
    private FirebaseUser user;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar circProgressBar;
    private DatabaseReference mDatabaseRef;
    private List<uploads> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        circProgressBar=findViewById(R.id.progress_circular);
        mRecyclerView=findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads= new ArrayList<uploads>();
        user= FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail().trim();
        for(int i=0;email.charAt(i)!='@';i++){
            node+=email.charAt(i);
        }
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Farmer/"+node+"/Products");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    String Url=postSnapshot.child("imagrurl").getValue(String.class);
                    String ProdName=postSnapshot.child("name").getValue(String.class);
                    String price=postSnapshot.child("quantity").getValue(String.class);
                    uploads upload=new uploads(ProdName,Url,price);
                    mUploads.add(upload);
                    circProgressBar.setVisibility(View.INVISIBLE);
                }

                mAdapter=new ImageAdapter(ImagesActivity.this,mUploads);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                circProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        //mAdapter=new ImageAdapter(ImagesActivity.this,mUploads);
        //mRecyclerView.setAdapter(mAdapter);
    }
}
