package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class User_Account extends AppCompatActivity implements View.OnClickListener {
    Dialog dialog;
    Button b, b1, b2;
    ListView lv;
    EditText editText;
    ArrayList<String> s1 = new ArrayList<String>();
    SQLiteDatabase db;
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Account.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        b = (Button) findViewById(R.id.button2);
        b1 = (Button) findViewById(R.id.button3);
        editText = (EditText) findViewById(R.id.editText);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);
        db = openOrCreateDatabase("CATTLEDAtA", MODE_PRIVATE, null);

    }

    @Override
    public void onClick(View v) {
        s1 = new ArrayList<String>();
        switch (v.getId()) {
            case R.id.button2:
                dialog = new Dialog(this);
                String s = editText.getText().toString();
                String a;
                dialog.setContentView(R.layout.listview_for_all);
                lv = (ListView) dialog.findViewById(R.id.lv_list);
                Cursor c;
                c = db.rawQuery("SELECT * FROM UserRegister WHERE username ='" + s + "';", null);
                int len = c.getCount();
                c.moveToFirst();
                if (len > 0) {
                    a = "USERNAME   : " + s;
                    s1.add(a);
                    a = "PASSWORD   : ";
                    a += c.getString(c.getColumnIndex("password"));
                    s1.add(a);
                    a = "Email_ID   : ";
                    a += c.getString(c.getColumnIndex("email_id"));
                    s1.add(a);
                    a = "MOBILE _NO : ";
                    a += c.getString(c.getColumnIndex("mn"));
                    s1.add(a);
                }
                editText.setText("");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s1);
                lv.setAdapter(adapter);
                dialog.show();
                //   db.close();
                break;
            case R.id.button3:
                e = (EditText) findViewById(R.id.pwd);
                //ContentValues contentValues=new ContentValues();
                String a1 = e.getText().toString();
                String b = editText.getText().toString();
                //contentValues.put("password",a1);
                db.execSQL("UPDATE UserRegister SET password ='" + a1 + "'WHERE username ='" + b + "';");
                Toast.makeText(getBaseContext(), "Updated Successully", Toast.LENGTH_SHORT).show();
                e.setText("");
                editText.setText("");
                break;

        }
    }
}