package com.kapul.hobbyclub;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class BookReviewActivity extends AppCompatActivity {

    private static final String TAG = BookReviewActivity.class.getSimpleName();
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView ivUploadBook;
    private EditText etNameBook, etReviewBook;
    private ProgressBar progressBar;

    private StorageReference storageReference;
    BottomNavigationView mainNavigation;
    DatabaseReference databaseReference;
    String uid;

    private StorageTask storageTask;

    private Uri imageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_review);

        Button btnChoose = findViewById(R.id.btnChooseBook);
        Button btnUpload = findViewById(R.id.btnUploadBook);
        ivUploadBook = findViewById(R.id.ivUploadBook);
        etNameBook = findViewById(R.id.etNameBook);
        etReviewBook = findViewById(R.id.etReviewBook);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        storageReference = FirebaseStorage.getInstance().getReference("Book/Review" + System.currentTimeMillis());
        databaseReference = FirebaseDatabase.getInstance().getReference("Book/Review");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storageTask != null && storageTask.isInProgress())
                {
                    Toast.makeText(BookReviewActivity.this, "Uploading in progress", Toast.LENGTH_SHORT).show();
                }
                else
                uploadFile();
            }
        });

        mainNavigation = findViewById(R.id.mainNavigation);

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(BookReviewActivity.this, MainActivity.class));
                        return true;


                    case R.id.account:
                        mainNavigation.setItemBackgroundResource(R.drawable.gradient_navigation_bar_account);

                        if (currentUser != null) {
                            uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String type = dataSnapshot.child("type").getValue().toString();
                                    String club = dataSnapshot.child("club_name").getValue().toString();
                                    if (type.equals("Member")) {
                                        startActivity(new Intent(BookReviewActivity.this, LoggedUserActivity.class));
                                        Toast.makeText(BookReviewActivity.this, "You're Logged in as Member", Toast.LENGTH_SHORT).show();

                                    } else if (type.equals("Admin")) {
                                        switch (club) {
                                            case "Adventure Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminAdventureActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin of Adventure Club", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Astronomy Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminAstronomyActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Book Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminBookActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Dance Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminDanceActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Film Sector Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminFilmActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Fine Arts Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminArtsActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                            case "Photography Club":
                                                startActivity(new Intent(BookReviewActivity.this, AdminPhotographyActivity.class));
                                                Toast.makeText(BookReviewActivity.this, "You're Logged in as Admin", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }else {
                            startActivity(new Intent(BookReviewActivity.this, UserActivity.class));
                        }
                    default:
                        return false;
                }
            }
        });

    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageuri = data.getData();

            Picasso.with(this).load(imageuri).into(ivUploadBook);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        progressBar.setVisibility(View.VISIBLE);

        final String name = etNameBook.getText().toString().trim();
        final String review = etReviewBook.getText().toString().trim();

        if (name.isEmpty()) {
            etNameBook.setError("Please enter Name");
            etNameBook.requestFocus();
        } else if (review.isEmpty()) {
            etReviewBook.setError("Please add Review");
            etReviewBook.requestFocus();
        }else if (imageuri != null) {
            storageReference.putFile(imageuri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Log.e(TAG, "then: " + downloadUri.toString());

                        Toast.makeText(BookReviewActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();

                        BookReview bookReview = new BookReview(name, review,
                                downloadUri.toString());

                        databaseReference.push().setValue(bookReview);

                        startActivity(new Intent(BookReviewActivity.this, BookHomeActivity.class));
                    } else {
                        Toast.makeText(BookReviewActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
