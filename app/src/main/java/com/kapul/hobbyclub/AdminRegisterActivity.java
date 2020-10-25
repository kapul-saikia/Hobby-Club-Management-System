package com.kapul.hobbyclub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AdminRegisterActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etName, etEmail, etPassword, etCPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);
        TextView tvAdmin = findViewById(R.id.tvAdmin);
        TextView tvLogin = findViewById(R.id.tvLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        final String club_name = Objects.requireNonNull(getIntent().getStringExtra("name")).trim();

        firebaseAuth = FirebaseAuth.getInstance();

        final String type = "Admin";


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirm_password = etCPassword.getText().toString().trim();


                if (name.isEmpty()) {
                    etName.setError("Please enter your name");
                    etName.requestFocus();
                } else if (email.isEmpty()) {
                    etEmail.setError("Please enter your email");
                    etEmail.requestFocus();
                } else if (password.isEmpty()) {
                    etPassword.setError("Please enter your Password");
                    etPassword.requestFocus();
                } else if (confirm_password.isEmpty()) {
                    etCPassword.setError("Please enter your Password again");
                    etCPassword.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Admin admin;
                                admin = new Admin(name, email, club_name, type);

                                FirebaseDatabase.getInstance().getReference("Users").
                                        child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).
                                        setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(AdminRegisterActivity.this, "Admin Registration Successful", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(AdminRegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                            Toast.makeText(AdminRegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                Toast.makeText(AdminRegisterActivity.this, "Registration Failed all", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
