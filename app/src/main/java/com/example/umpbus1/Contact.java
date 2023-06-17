package com.example.umpbus1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class Contact extends AppCompatActivity  {

    private Button phone,email;
    private ImageButton map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        phone = (Button) findViewById(R.id.callButton);
        email = (Button) findViewById(R.id.emailButton);

        ImageButton map = (ImageButton) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("geo: 3.545504125084981, 103.42519268066603"));
                startActivity(intent);
            }
        } );


        phone.setOnClickListener(v -> {
            Intent telefon = new Intent(Intent.ACTION_DIAL);
            telefon.setData(Uri.parse("tel:094215022"));
            startActivity(telefon);
        });
        email.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "security@ump.edu.my");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Asking for information");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });



    }



}