package com.rahul.farmerproject;
        import androidx.annotation.NonNull;
        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth fa;
    FirebaseAuth.AuthStateListener authState;
    EditText email,pass;
    String Email,Pass;
    RadioButton rb1,rb2;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.em);
        pass = findViewById(R.id.pass);
        rb1=findViewById(R.id.radioButton1);
        rb2=findViewById(R.id.radioButton2);
        fa = FirebaseAuth.getInstance();
    }

    public void loginClick(View view){
        Email=email.getText().toString().trim();
        Pass=pass.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(LoginActivity.this,"Enter your Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Pass)){
            Toast.makeText(LoginActivity.this,"Enter your Password",Toast.LENGTH_SHORT).show();
            return;
        }
//        if(rb1.isChecked()){
//            fa = FirebaseAuth.getInstance();
//        }
        fa.signInWithEmailAndPassword(Email,Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //FirebaseUser user=fa.getCurrentUser();
                            finishAffinity();
                            if(rb1.isChecked()) {
                                Intent it = new Intent(LoginActivity.this, FarmerDashboard.class);
                                startActivity(it);
                            }
                            else {
                                Intent it = new Intent(LoginActivity.this, BuyerDashboard.class);
                                startActivity(it);
                            }
                        }
                        else
                            Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}