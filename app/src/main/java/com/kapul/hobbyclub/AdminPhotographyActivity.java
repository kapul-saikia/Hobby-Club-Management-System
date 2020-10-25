package com.kapul.hobbyclub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AdminPhotographyActivity extends AppCompatActivity
{

    private EditText etTOTD, etTOTW, etTOTM, etUpcomingActivity, etDate;
    DatabaseReference databaseReference;
    String value;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_photography);

        etTOTD = findViewById(R.id.etTOTD);
        etTOTW = findViewById(R.id.etTOTW);
        etTOTM = findViewById(R.id.etTOTM);
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
                datePickerDialog = new DatePickerDialog(AdminPhotographyActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        etDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Button btnTOTD = findViewById(R.id.btnTOTD);
        Button btnTOTW = findViewById(R.id.btnTOTW);
        Button btnTOTM = findViewById(R.id.btnTOTM);
        Button btnPublish = findViewById(R.id.btnPublish);

        TextView tvManage = findViewById(R.id.tvManage);
        TextView tvSignOut = findViewById(R.id.tvSignOut);

        btnTOTD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (etTOTD.getText().toString().isEmpty())
                {
                    Toast.makeText(AdminPhotographyActivity.this, "Please enter today's Theme", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String totd = etTOTD.getText().toString().trim();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Events");
                    DatabaseReference databaseReferenceChild = databaseReference.child("ThemeOfTheDay");
                    databaseReferenceChild.setValue(totd);
                    Toast.makeText(AdminPhotographyActivity.this, "Theme Of The Day is set", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnTOTW.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (etTOTW.getText().toString().isEmpty())
                {
                    Toast.makeText(AdminPhotographyActivity.this, "Please enter Theme Of The Week", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String totw = etTOTW.getText().toString().trim();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Events");
                    DatabaseReference databaseReferenceChild = databaseReference.child("ThemeOfTheWeek");
                    databaseReferenceChild.setValue(totw);
                    Toast.makeText(AdminPhotographyActivity.this, "Theme Of The Week is set", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTOTM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (etTOTM.getText().toString().isEmpty())
                {
                    Toast.makeText(AdminPhotographyActivity.this, "Please enter Theme Of The Month", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String totm = etTOTM.getText().toString().trim();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Events");
                    DatabaseReference databaseReferenceChild = databaseReference.child("ThemeOfTheMonth");
                    databaseReferenceChild.setValue(totm);
                    Toast.makeText(AdminPhotographyActivity.this, "Theme Of The Month is set", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPhotographyActivity.this, ShowMembersActivity.class);
                value = "Photography Club";
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
                    databaseReference = FirebaseDatabase.getInstance().getReference("Photography Club").child("Upcoming");
                    databaseReference.setValue(notification);
                }
            }
        });

        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(AdminPhotographyActivity.this, MainActivity.class));
            }
        });


    }
}
