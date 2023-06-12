package com.example.umpbus1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class Tracker extends AppCompatActivity {
    TextView confirmed;
    private Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        if (date.isEmpty()){
            Toast.makeText(Tracker.this, "Book First!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Tracker.this,MenuActivity.class);
            startActivity(intent);
        }else{
            confirmed = (TextView) findViewById(R.id.trackerBanner);
            confirmed.setText("Your Booking, " + date + " is on the way!");
            goHome=(Button) findViewById(R.id.homeB);
        }

        confirmed = (TextView) findViewById(R.id.trackerBanner);
        confirmed.setText("Your Booking, " + date + " is on the way!");
        goHome=(Button) findViewById(R.id.homeB);


        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tracker.this, MenuActivity.class));
            }
        });

    }

}