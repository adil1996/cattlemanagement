package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class User_other_options extends AppCompatActivity implements View.OnClickListener{
   Button b,b1,b2,b3;
    ArrayList<String> s2=new ArrayList<String>();
    ArrayList<Bitmap>s3=new ArrayList<Bitmap>();

    Dialog  dialog;
    SQLiteDatabase db1;
   ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_other_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    b= (Button) findViewById(R.id.see_orders);
        b1= (Button) findViewById(R.id.update_order);
        b2= (Button) findViewById(R.id.delete_order);
        b3= (Button) findViewById(R.id.add_order);
        b. setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         s2=new ArrayList<String>();
        s3=new ArrayList<Bitmap>();
        Cursor cursor;
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.listview_for_all);
        lv= (ListView) dialog.findViewById(R.id.lv_list);
        db1 = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        cursor = db1.rawQuery("SELECT * FROM P_INFO;", null);
        int len1 = cursor.getCount();
        cursor.moveToFirst();
        while (len1 > 0) {
            String name1 = cursor.getString(cursor.getColumnIndex("P_ID"));
            s2.add(name1);
            byte[] data=cursor.getBlob(cursor.getColumnIndex("Image"));
            ByteArrayInputStream image1=new ByteArrayInputStream(data);
            Bitmap theImage= BitmapFactory.decodeStream(image1);
            s3.add(theImage);
            cursor.moveToNext();
            len1--;
        }
        Custom2 adapter1=new Custom2(this,s2,s3);
        lv.setAdapter(adapter1);
        dialog.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(User_other_options.this,Show_orders.class);
                intent.putExtra("first",s2.get(position));
                startActivity(intent);
            }
        });

    }

    /**
     * Created by ADIL on 10-03-2017.
     */

   }
