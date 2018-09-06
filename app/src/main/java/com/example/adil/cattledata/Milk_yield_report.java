package com.example.adil.cattledata;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Milk_yield_report extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    Spinner spinner;
    String [] item={"","DAILY","MONTHLY","YEARLY"};
    Dialog dialog;
    EditText ca_no,ca_name,qty,e,e1,e2,e3;
    String date,query;
    Button save;
    SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_yield_report);
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
        ca_no= (EditText) findViewById(R.id.c_no);
        ca_name= (EditText) findViewById(R.id.c_name);
        qty= (EditText) findViewById(R.id.Quantity);
        save= (Button) findViewById(R.id.save_milk);
        save.setOnClickListener(this);
        db=openOrCreateDatabase("ADIL",MODE_PRIVATE,null);
        String string="CREATE TABLE IF NOT EXISTS Milk_Dailly (Date VARCHAR,CA_NO VARCHAR,CA_NAME VARCHAR,QUANTITY VARCHAR);";
        String string1="CREATE TABLE IF NOT EXISTS Milk_Monthly (Month VARCHAR,Year VARCHAR,CA_NO VARCHAR,CA_NAME VARCHAR,QUANTITY VARCHAR);";
        String string2="CREATE TABLE IF NOT EXISTS Milk_Yearly (Year VARCHAR,Date VARCHAR,CA_NO VARCHAR,CA_NAME VARCHAR,QUANTITY VARCHAR);";
        db.execSQL(string);
        db.execSQL(string1);
        db.execSQL(string2);
        spinner= (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 1:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.camel);
                e= (EditText) dialog.findViewById(R.id.dalog_date);
                dialog.show();
                e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        final Calendar c=Calendar.getInstance();
                        final int mYear=c.get(Calendar.YEAR);
                        final int mMonth=c.get(Calendar.MONTH);
                        final int mDay=c.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Milk_yield_report.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                e.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });
                date=e.getText().toString();
                dialog.show();
                query="Milk_Dailly";
                break;
            case 2:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.buffalo);
                e1= (EditText) dialog.findViewById(R.id.month);
                e2= (EditText) dialog.findViewById(R.id.year);
                dialog.show();
                query="Milk_Monthly";
                break;
            case  3:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.year);
                e3= (EditText) dialog.findViewById(R.id.year1);
                dialog.show();
                query="Milk_Yearly";
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(query.equals("Milk_Dailly"))
        {
            date=e.getText().toString();
            e.setText(date);
            contentValues.put("Date",date);
            dialog.show();
        }
        else  if (query.equals("Milk_Monthly"))
        {
            String  month=e1.getText().toString();
            String year=e2.getText().toString();
            contentValues.put("Month",month);
            contentValues.put("Year",year);
            dialog.show();
        }
        else
        {
            String year1=e3.getText().toString();
            contentValues.put("Year",year1);
            dialog.show();
        }
        String trans=ca_no.getText().toString();
        String labor=ca_name.getText().toString();
        String food=qty.getText().toString();
        contentValues.put("CA_NO",trans);
        contentValues.put("CA_NAME",labor);
        contentValues.put("QUANTITY",food);
        db.insert(query,null,contentValues);
        Toast.makeText(this,"Suceessfully Inserted!!!"+query,Toast.LENGTH_SHORT).show();
    }
}
