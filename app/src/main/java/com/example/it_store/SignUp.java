package com.example.it_store;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity {
        EditText edtpass,edtemail;
        TextView tv_login;
        Button btn_re;
        private FirebaseAuth firebaseAuth,mAuth;
        private static final String TAG="";
        private final  static  int RC_SING_IN=111;

        @Override
        public void onStart() {
            super.onStart();
            // ktra nguoi dung dang nhap hay chua
          FirebaseUser currentUser = mAuth.getCurrentUser();
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup);
            addControls();
            addEvents();
        }
            private void addEvents(){
                btn_re.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email_1 = edtemail.getText().toString().trim();
                        String pass_1 = edtpass.getText().toString().trim();
                        Log.d("TAG", email_1);
                        if (TextUtils.isEmpty(email_1)) {
                            edtemail.setError("Require Input Email!!");
                            return;
                        }
                        if (TextUtils.isEmpty(pass_1)) {
                            edtpass.setError("Require Input Password!!");
                            return;
                        }
                        mAuth.createUserWithEmailAndPassword(email_1, pass_1)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUp.this, "Create User", LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), Home.class));
                                        } else {
                                            Toast.makeText(SignUp.this, "Error " + task.getException(), LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        tv_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        });
                    }
                });
            }

        private void addControls() {
            edtpass= findViewById(R.id.pass1);
            edtemail= findViewById(R.id.email1);
            tv_login= findViewById(R.id.tv_login);
            btn_re= findViewById(R.id.button1);
            mAuth=FirebaseAuth.getInstance();
      }

   }
