package com.example.umpbus1;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Report extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ImageView imgV;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    private EditText etReport;
    private Button reportSend, reportView, manageReport;
    private ProgressBar progressBar;
    private String userID;
    DBHelper DB;
    Button insertPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        imgV = findViewById(R.id.imageView2);
        insertPic = (Button) findViewById(R.id.insertPic);
        etReport = (EditText) findViewById(R.id.reportText);
        reportSend=(Button) findViewById(R.id.reportSend);
        reportView=(Button) findViewById(R.id.reportHistoryB);
        manageReport=(Button) findViewById(R.id.manageB);

        DB =new DBHelper(this);
        //Request For Camera Permission
        if (ContextCompat.checkSelfPermission(Report.this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Report.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        insertPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        manageReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Report.this, ReportCRUD.class));
            }
        });
        reportSend.setOnClickListener(view -> {
            String report = etReport.getText().toString().trim();

            Boolean checkinsertdata = DB.insertuserdata(report);
            if (checkinsertdata==true)
                Toast.makeText(Report.this, "new entry inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Report.this, "new entry not inserted", Toast.LENGTH_SHORT).show();
            });
        reportView.setOnClickListener(view -> {
            Cursor res = DB.getdata();
            if(res.getCount()==0){
                Toast.makeText(Report.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append("ID: "+res.getString(0)+"\n");
                buffer.append("Report: "+res.getString(1)+"\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(Report.this);
            builder.setCancelable(true);
            builder.setTitle("Report History");
            builder.setMessage(buffer.toString());
            builder.show();
        });

    }
}