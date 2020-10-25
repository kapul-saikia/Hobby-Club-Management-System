package com.kapul.hobbyclub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AdminAdventureActivity extends AppCompatActivity {

    private EditText etUpcomingActivity, etDate;
    DatabaseReference databaseReference;
    String value;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adventure);

        etUpcomingActivity = findViewById(R.id.etUpcomingActivity);
        etDate= findViewById(R.id.etDate);
        etDate.setInputType(InputType.TYPE_NULL);
        etDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar calender = Calendar.getInstance();
                int day = calender.get(Calendar.DAY_OF_MONTH);
                int month = calender.get(Calendar.MONTH);
                int year = calender.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AdminAdventureActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        etDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        Button btnPublish = findViewById(R.id.btnPublish);

        TextView tvManage = findViewById(R.id.tvManage);
        TextView tvSignOut = findViewById(R.id.tvSignOut);

        tvManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAdventureActivity.this, ShowMembersActivity.class);
                value = "Adventure Club";
                intent.putExtra("name of the club", value);
                startActivity(intent);
            }
        });

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upcomingActivity = etUpcomingActivity.getText().toString().trim();
                String date = etDate.getText().toString().trim();

                if (upcomingActivity.isEmpty()) {
                    etUpcomingActivity.setError("Please enter UpcomingActivity");
                    etUpcomingActivity.requestFocus();
                } else if (date.isEmpty()) {
                    etDate.setError("Please enter Date");
                    etDate.requestFocus();
                }else {
                    Notification notification = new Notification(upcomingActivity, date);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Adventure Club").child("Upcoming");
                    databaseReference.setValue(notification);
                }
            }
        });

        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(AdminAdventureActivity.this, MainActivity.class));
            }
        });



    }
}
