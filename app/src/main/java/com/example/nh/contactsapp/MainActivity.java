package com.example.nh.contactsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText email,name,phone;
    Button btn;
    ArrayList<Data> grougOfdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.email);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        btn=(Button) findViewById(R.id.btn);
        grougOfdata=new ArrayList<Data>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Add new Contact");
        builder1.setMessage("Are you sure ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String nam=name.getText().toString();
                        String emai=email.getText().toString();
                        String phon=phone.getText().toString();
                        Data data=new Data(nam,phon,emai);
                        grougOfdata.add(data);

                        //insert Data object into database (one by one)
                        DatabaseHelper db=new DatabaseHelper(MainActivity.this);
                        db.insertdata(data);
                        db.close();

                        Intent i=new Intent(MainActivity.this,ShowList.class);
                        i.putExtra("Data",grougOfdata);  //sending arraylist Of Data Object
                        startActivity(i);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.contactsss:
                Intent i=new Intent(MainActivity.this,ShowList.class);
                startActivity(i);
                break;
            case R.id.rate:
                Intent in=new Intent(MainActivity.this,RateActivity.class);
                startActivity(in);
                break;
        }
        return true;
    }
}
