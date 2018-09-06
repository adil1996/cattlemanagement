package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class feedback extends AppCompatActivity implements View.OnClickListener{
RatingBar ratingBar;
    ArrayList<String>arrayList=new ArrayList<String>();
    Dialog dialog;
    EditText e,e1,e2;
    Button save;
    ListView listView;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar2);
        e= (EditText) findViewById(R.id.editText6);
        e1= (EditText) findViewById(R.id.editText8);
        e2= (EditText) findViewById(R.id.textView9);
        save= (Button) findViewById(R.id.b1);
        save.setOnClickListener( this);


    }

    @Override
    public void onClick(View v) {
        a="Name : ";
        String s=e.getText().toString();
        a+=s;
        arrayList.add(a);
        String s1=e1.getText().toString();
        a="Email-id :";
        a+=s1;
        arrayList.add(a);
        String s2=e2.getText().toString();
        a="Feedback :";
        a+=s2;
        arrayList.add(a);
        float f=ratingBar.getRating(); 
        a="Rating :";
        a+=f;
        arrayList.add(a);
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.listview_for_all);
        listView= (ListView) dialog.findViewById(R.id.lv_list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        dialog.show();
    }
}
