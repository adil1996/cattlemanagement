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
import android.widget.GridView;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class User_cattle_list extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{

    SQLiteDatabase db;
    Dialog dialog;
    String a;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<Bitmap>bList=new ArrayList<Bitmap>();
    GridView gridView;
    ListView lv;
    ArrayList<String>arrayList = new ArrayList<String>();
    ArrayList<Integer>integerArrayList=new ArrayList<Integer>();
    Button slect_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cattle_list);
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

        slect_p= (Button) findViewById(R.id.select_item);
        slect_p.setOnClickListener(this);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.listview_for_all);
        lv = (ListView) dialog.findViewById(R.id.lv_list);
        arrayList.add("COW");
        arrayList.add("GOAT");
        arrayList.add("BUFALLO");
        integerArrayList.add(R.drawable.cow1);
        integerArrayList.add(R.drawable.go2);
        integerArrayList.add(R.drawable.buf1);


        db = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        gridView = (GridView) findViewById(R.id.grid_view2);
        Custom1 custom1 = new Custom1(this,arrayList, integerArrayList);
        gridView.setAdapter(custom1);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        list=new ArrayList<String>();
        bList=new ArrayList<Bitmap>();
        switch (position) {
            case 0:
                Cursor c;
                c = db.rawQuery("SELECT * FROM COW;", null);
                int len = c.getCount();
                int i = 0;
                a="COW";
                c.moveToFirst();
                while (len > 0) {
                    String name1 = c.getString(c.getColumnIndex("cattle_no"));
                    list.add(name1);
                    byte[] data=c.getBlob(c.getColumnIndex("Image"));
                    ByteArrayInputStream image1=new ByteArrayInputStream(data);
                    Bitmap theImage= BitmapFactory.decodeStream(image1);
                    bList.add(theImage);
                    c.moveToNext();
                    len--;
                }
                Custom2 adapter=new Custom2(this,list,bList);
                lv.setAdapter(adapter);
                dialog.show();
                break;
            case 1:
                Cursor c1;
                c1 = db.rawQuery("SELECT * FROM GOAT;", null);
                int len1 = c1.getCount();
                a="GOAT";
                int i1 = 0;
                c1.moveToFirst();
                while (len1 > 0) {
                    String name1 = c1.getString(c1.getColumnIndex("cattle_no"));
                    list.add(name1);
                    byte[] data=c1.getBlob(c1.getColumnIndex("Image"));
                    ByteArrayInputStream image1=new ByteArrayInputStream(data);
                    Bitmap theImage= BitmapFactory.decodeStream(image1);
                    bList.add(theImage);
                    c1.moveToNext();
                    len1--;
                }
                Custom2 adapter1=new Custom2(this,list,bList);
                lv.setAdapter(adapter1);
                dialog.show();
                break;
            case 2:
                Cursor c2;
                c2 = db.rawQuery("SELECT * FROM BUFFALO;", null);
                int len2 = c2.getCount();
                int i2 = 0;
                a="BUFFALO";
                c2.moveToFirst();
                while (len2 > 0) {
                    String name1 = c2.getString(c2.getColumnIndex("cattle_no"));
                    list.add(name1);
                    byte[] data=c2.getBlob(c2.getColumnIndex("Image"));
                    ByteArrayInputStream image1=new ByteArrayInputStream(data);
                    Bitmap theImage= BitmapFactory.decodeStream(image1);
                    bList.add(theImage);
                    c2.moveToNext();
                    len2--;
                }
                Custom2 adapter2=new Custom2(this,list,bList);
                lv.setAdapter(adapter2);
                dialog.show();
                break;
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String b= list.get(position);
                Intent intent = new Intent(User_cattle_list.this, ShowData.class);
                intent.putExtra("first",a);
                intent.putExtra("second",b);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        list=new ArrayList<String>();
        bList=new ArrayList<Bitmap>();

        Dialog dialog1=new Dialog(this);
        dialog1.setContentView(R.layout.product_list);
        GridView gridView1= (GridView) dialog1.findViewById(R.id.product_grid);
        Cursor c;
        c = db.rawQuery("SELECT * FROM Product;", null);
        int len = c.getCount();
        a="Product";
        c.moveToFirst();
        while (len > 0) {
            String name1 = c.getString(c.getColumnIndex("cattle_no"));
            list.add(name1);
            byte[] data=c.getBlob(c.getColumnIndex("Image"));
            ByteArrayInputStream image1=new ByteArrayInputStream(data);
            Bitmap theImage= BitmapFactory.decodeStream(image1);
            bList.add(theImage);
            c.moveToNext();
            len--;
        }
        Custom2 adapter=new Custom2(this,list,bList);
        gridView1.setAdapter(adapter);
        dialog1.show();
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String b= list.get(position);
                Intent intent = new Intent(User_cattle_list.this, Product_display.class);
                intent.putExtra("first",a);
                intent.putExtra("second",b);
                startActivity(intent);
            }
        });
    }
}
