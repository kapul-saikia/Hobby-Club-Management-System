package com.kapul.hobbyclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity
{

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvRegister, tvFgtPassword;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);


        tvRegister = findViewById(R.id.tvRegister);
        tvFgtPassword = findViewById(R.id.tvFgtPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty())
                {
                    etEmail.setError("Please enter your email");
                    etEmail.requestFocus();
                }

                else if (password.isEmpty())
                {
                    etPassword.setError("Please enter your Password");
                    etPassword.requestFocus();
                }else {

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        String type = dataSnapshot.child("type").getValue().toString();
                                        String club = dataSnapshot.child("club_name").getValue().toString();
                                          if (type.equals("Member")) {
                                                startActivity(new Intent(LoginActivity.this, LoggedUserActivity.class));
                                                Toast.makeText(LoginActivity.this, "You're Logged in as Member", Toast.LENGTH_SHORT).show();

                                            }
                                          else if (type.equals("Admin")) {
                                              switch (club) {
                                                  case "Adventure Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminAdventureActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin of Adventure Club", Toast.LENGTH_SHORT).show();
                                                      break;
                                                  case "Astronomy Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminAstronomyActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                      break;
                                                  case "Book Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminBookActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                      break;
                                                  case "Dance Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminDanceActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                      break;
                                                  case "Film Sector Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminFilmActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                      break;
                                                  case "Fine Arts Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminArtsActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                      break;
                                                  case "Photography Club":
                                                      startActivity(new Intent(LoginActivity.this, AdminPhotographyActivity.class));
                                                      Toast.makeText(LoginActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                      break;
                                              }
                                          }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                    });
                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this, RegisterOptionActivity.class));
            }
        });

        tvFgtPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this, PasswordReset.class));
            }
        });
    }
}
