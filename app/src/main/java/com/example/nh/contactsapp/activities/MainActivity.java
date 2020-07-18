package com.example.nh.contactsapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nh.contactsapp.database.DatabaseHelper;
import com.example.nh.contactsapp.R;
import com.example.nh.contactsapp.models.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout root;
    private EditText email,name,phone;
    private Button btn;
    private ArrayList<Data> grougOfdata;
    private String nam,emai,phon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.email);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        btn=(Button) findViewById(R.id.btn);
        root=findViewById(R.id.root);

        grougOfdata=new ArrayList<Data>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nam=name.getText().toString();
                emai=email.getText().toString();
                phon=phone.getText().toString();

                //check fields if its empty
                if (TextUtils.isEmpty(nam) || TextUtils.isEmpty(emai) ||TextUtils.isEmpty(phon)) {
                    Toast.makeText(MainActivity.this, "please enter empty field", Toast.LENGTH_SHORT).show();
                }else {
                    openDialog();
                }

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

                            insertData(nam,phon,emai);
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

    private void insertData(String nam,String phon,String emai){
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
}
