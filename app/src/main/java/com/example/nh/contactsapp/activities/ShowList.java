package com.example.nh.contactsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nh.contactsapp.database.DatabaseHelper;
import com.example.nh.contactsapp.R;
import com.example.nh.contactsapp.adapters.Customlistview;
import com.example.nh.contactsapp.models.Data;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

    ListView list;
    EditText search;
    ArrayList<Data> tempArrayList;
    Customlistview adapter;
    ArrayList<Data> arrFromDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        list=(ListView)findViewById(R.id.list);
        search=(EditText) findViewById(R.id.search);

        DatabaseHelper db=new DatabaseHelper(this);
         arrFromDatabase=db.getAllrows();   // get Arraylist of Data Object from database

      //  final ArrayList<Data> arrFromIntent= (ArrayList<Data>) getIntent().getParcelableExtra("Data");   // receive arraylist of Data Object from intent

        db.close();
        adapter= new Customlistview(this,arrFromDatabase);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ShowList.this,Single.class);
                if(tempArrayList==null){
                    intent.putExtra("group",arrFromDatabase.get(i));   // sending selected Data Object
                   //  intent.putExtra("group",arrFromIntent.get(i));         // sending selected Data Object
                }
                else{
                    intent.putExtra("group",tempArrayList.get(i));   // sending selected Data Object
                }
              startActivityForResult(intent,1);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // Make another arraylist of Data Object which object has contain the same name input in edittext , then show this array in listview
                tempArrayList = new ArrayList<Data>();
                for(int j=0;j<arrFromDatabase.size();j++){   // or arrFromIntent
                    if (arrFromDatabase.get(j).getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        tempArrayList.add(arrFromDatabase.get(j));
                    }
                }
                Customlistview adapter = new Customlistview(ShowList.this,tempArrayList);
                list.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1 && resultCode==RESULT_OK){
           arrFromDatabase.clear();
           DatabaseHelper db=new DatabaseHelper(this);
            arrFromDatabase=db.getAllrows();
            db.close();
            adapter= new Customlistview(this,arrFromDatabase);
            list.setAdapter(adapter);
        }

    }




}
