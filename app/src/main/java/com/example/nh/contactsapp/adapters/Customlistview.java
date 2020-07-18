package com.example.nh.contactsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nh.contactsapp.R;
import com.example.nh.contactsapp.models.Data;

import java.util.ArrayList;

/**
 * Created by NH on 5/4/2018.
 */

public class Customlistview extends ArrayAdapter {

    Context context;
    ArrayList<Data> group;

    public Customlistview(@NonNull Context context, ArrayList<Data> group) {
        super(context, R.layout.customlist, group);

        this.group=group;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate( R.layout.customlist, null );

        TextView name=(TextView)view.findViewById(R.id.nam);
        TextView number=(TextView)view.findViewById(R.id.num);

        name.setText(group.get(position).getName());
        number.setText(group.get(position).getPhone());


        return view;
    }
}
