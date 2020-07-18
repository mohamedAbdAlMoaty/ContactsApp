package com.example.nh.contactsapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nh.contactsapp.database.DatabaseHelper;
import com.example.nh.contactsapp.R;
import com.example.nh.contactsapp.models.Data;

public class Single extends AppCompatActivity {

    TextView name1, email1, phone1;
    Button call,sms,remove;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        name1 = (TextView) findViewById(R.id.namee);
        email1 = (TextView) findViewById(R.id.emaill);
        phone1 = (TextView) findViewById(R.id.numberr);
        call = (Button) findViewById(R.id.called);
        sms=(Button) findViewById(R.id.sms);
        remove=(Button)findViewById(R.id.remove);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone1.getText().toString()));
                startActivity(intent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"+phone1.getText().toString()));
                startActivity(intent);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(Single.this);
                db.deleterow(key);
                db.close();
                setResult(RESULT_OK);
              finish();
            }
        });

        Data x= (Data) getIntent().getParcelableExtra("group");  //recive selected Data Object
        key=x.getId();
        name1.setText(x.getName());
        email1.setText(x.getEmail());
        phone1.setText(x.getPhone());



    }

    public void ok(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email1.getText().toString()});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Single.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
