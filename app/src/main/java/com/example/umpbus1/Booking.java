package com.example.umpbus1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Booking extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText dateEditText;
    private Button toSchedule;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference busScheduleRef = database.getReference("Bus Schedule");


        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        toSchedule = (Button) findViewById(R.id.confirmBookB);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                dateEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                String date = dateEditText.getText().toString().trim();
                toSchedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(Booking.this, Tracker.class);
                        intent.putExtra("date",date);
                        startActivity(intent);
                    }
                });
            }
        });



    }
}