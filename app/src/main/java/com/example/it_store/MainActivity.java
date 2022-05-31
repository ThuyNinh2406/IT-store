package com.example.it_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    EditText edituser , editpassword;
    Button btndangnhap;
    TextView tv_register,sign_gg;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        Anhxa();
    }

    private void Anhxa() {
        edituser =  findViewById(R.id.edittextemail);
        editpassword = findViewById(R.id.edittextpassword);
        btndangnhap =findViewById(R.id.button2);
        tv_register=findViewById(R.id.tv_register);
        sign_gg=findViewById(R.id.sign_gg2);
        mAuth= FirebaseAuth.getInstance();

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_1=edituser.getText().toString();
                String pass_1=editpassword.getText().toString();
                if(TextUtils.isEmpty(email_1))
                {
                    edituser.setError("Require Input Email!!");
                    return;
                }
                if(TextUtils.isEmpty(pass_1))
                {
                    editpassword.setError("Require Input Password!!");
                    return;
                }
                DangNhap(email_1, pass_1);
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    //funtion dang nhap
    private void DangNhap (String email, String pass){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Dang nhap thanh cong
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else{
                            // Dang nhap that bai
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
