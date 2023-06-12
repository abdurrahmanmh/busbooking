package com.example.umpbus1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportCRUD extends AppCompatActivity {
    private EditText etReportID;
    private Button reportUpdate, reportView, deleteReport;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crud);
        DB =new DBHelper(this);
        //String newReport;
        etReportID = (EditText) findViewById(R.id.reportID);
        reportUpdate=(Button) findViewById(R.id.updateB);
        reportView=(Button) findViewById(R.id.historyB);
        deleteReport=(Button) findViewById(R.id.DeleteB);

        reportView.setOnClickListener(view -> {


            Cursor res = DB.getdata();
            if(res.getCount()==0){
                Toast.makeText(ReportCRUD.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append("ID: "+res.getString(0)+"\n");
                buffer.append("Report: "+res.getString(1)+"\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(ReportCRUD.this);
            builder.setCancelable(true);
            builder.setTitle("Report History");
            builder.setMessage(buffer.toString());
            builder.show();
        });
        reportUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String reportID = etReportID.getText().toString().trim();

                //verify
                if(reportID.isEmpty()){
                    etReportID.setError("ID is required");
                    etReportID.requestFocus();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ReportCRUD.this);
                builder.setTitle("Update Report");

// Set up the input
                final EditText input = new EditText(ReportCRUD.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String newReport = input.getText().toString().trim();

                        Boolean checkupdatedata = DB.updateuserdata(Integer.parseInt(reportID), newReport);
                        if (checkupdatedata==true)
                            Toast.makeText(ReportCRUD.this, "new entry updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ReportCRUD.this, "new entry not updated", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        deleteReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String reportID = etReportID.getText().toString().trim();
                if(reportID.isEmpty()){
                    etReportID.setError("ID is required");
                    etReportID.requestFocus();
                    return;
                }
                Boolean checkdeletedata = DB.deleteuserdata(Integer.parseInt(reportID));
                if (checkdeletedata==true)
                    Toast.makeText(ReportCRUD.this, "entry deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ReportCRUD.this, "new entry not deleted", Toast.LENGTH_SHORT).show();
            }
        });




    }
}