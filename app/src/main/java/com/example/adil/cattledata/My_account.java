package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class My_account extends AppCompatActivity implements View.OnClickListener{
    Dialog dialog;
    Button b,b1,b2;
    ListView lv;
    EditText editText;
    ArrayList<String>s1=new ArrayList<String>();
    ArrayList<String>s2=new ArrayList<String>();
    ArrayList<Bitmap>s3=new ArrayList<Bitmap>();
    SQLiteDatabase db,db1;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
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
        b= (Button) findViewById(R.id.button2);
        b1= (Button) findViewById(R.id.button3);
      editText= (EditText) findViewById(R.id.editText);
        b2= (Button) findViewById(R.id.button4);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        db=openOrCreateDatabase("CATTLEDAtA",MODE_PRIVATE,null);

    }

    @Override
    public void onClick(View v) {
        s1=new ArrayList<String>();
        s2=new ArrayList<String>();
        s3=new ArrayList<Bitmap>();

        switch (v.getId())
        {
            case R.id.button2:
                dialog=new Dialog(this);
                String s=editText.getText().toString();
                String a;
                dialog.setContentView(R.layout.listview_for_all);
                lv= (ListView) dialog.findViewById(R.id.lv_list);
                Cursor c;
                c=db.rawQuery("SELECT * FROM AdminRegister WHERE username ='"+s+"';",null);
                int len=c.getCount();
                c.moveToFirst();
                if(len>0)
                {
                    a="USERNAME   : "+s;
                    s1.add(a);
                    a="PASSWORD   : ";
                    a+=c.getString(c.getColumnIndex("password"));
                    s1.add(a);
                    a="Email_ID   : ";
                    a+=c.getString(c.getColumnIndex("email_id"));
                    s1.add(a);
                    a="MOBILE _NO : ";
                    a+=c.getString(c.getColumnIndex("mn"));
                    s1.add(a);
                }
                editText.setText("");
                ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s1);
                lv.setAdapter(adapter);
                dialog.show();
             //   db.close();
                break;
            case R.id.button3:
                e= (EditText) findViewById(R.id.pwd);
                //ContentValues contentValues=new ContentValues();
                String a1=e.getText().toString();
                String b=editText.getText().toString();
                //contentValues.put("password",a1);
                        db.execSQL("UPDATE AdminRegister SET password ='"+a1+"'WHERE username ='"+b+"';");
                        Toast.makeText(getBaseContext(),"Updated Successully",Toast.LENGTH_SHORT).show();
                e.setText("");
                editText.setText("");
                break;
            case R.id.button4:
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
                        Intent intent=new Intent(My_account.this,Show_orders.class);
                        intent.putExtra("first",s2.get(position));
                        startActivity(intent);
                    }
                });
                break;

        }

    }
}
