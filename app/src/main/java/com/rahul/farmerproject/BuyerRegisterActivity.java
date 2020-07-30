package com.rahul.farmerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerRegisterActivity extends AppCompatActivity {
    private EditText Fname,Lname,Email,Password,number,Conpass;
    private Button Reg;
    private FirebaseAuth f;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);
        Fname=findViewById(R.id.first_name);
        Lname=findViewById(R.id.last_name);
        Password=findViewById(R.id.pass);
        Email=findViewById(R.id.em);
        Conpass=findViewById(R.id.confirmpass);
        number=findViewById(R.id.mob_no);
        f=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference("Buyer");
    }
    public boolean validateEmail(String emilip){
        System.out.println("Validate Email");
        //String emilip= ContactsContract.CommonDataKinds.Email.getText().toString().trim();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (emilip == null)
            return false;
        return pat.matcher(emilip).matches();
        //System.out.println(emilip);
    }

    public boolean validatePhone(String no){
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(no);
        return (m.find() && m.group().equals(no));
    }
    public void RegisterClick(View view){
        final String fname=Fname.getText().toString().trim();
        final String lname=Lname.getText().toString().trim();
        final String email=Email.getText().toString().trim();
        final String No=number.getText().toString().trim();
        final String pass=Password.getText().toString().trim();
        String cpass=Conpass.getText().toString().trim();
        if (validateEmail(email) && validatePhone(No)){
            if(pass.equals(cpass)) {
                f.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(BuyerRegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Farmer farmer=new Farmer(fname,lname,email,No);
                            db.child(No).setValue(farmer);
                            Toast.makeText(BuyerRegisterActivity.this,"Resistration Successful",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(BuyerRegisterActivity.this, BuyerDashboard.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(BuyerRegisterActivity.this,"Signup Unsuccessful",Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else
                Toast.makeText(BuyerRegisterActivity.this,"Password Didn't mach",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(BuyerRegisterActivity.this,"Enter Valid Details",Toast.LENGTH_SHORT).show();
        }
    }
}
