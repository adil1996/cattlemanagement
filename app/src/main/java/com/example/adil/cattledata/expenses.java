package com.example.adil.cattledata;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class expenses extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    EditText text1,text2,text3,text4,text5,text6,text7,text9,text10,e,e1,e2,e3;
    Button b;
    Spinner spinner2;
    String [] item={"","DAILY","MONTHLY","YEARLY"};
    SQLiteDatabase db;
    Dialog dialog;
    ContentValues contentValues=new ContentValues();
    String query,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text1=(EditText)findViewById(R.id.transport);
        text2=(EditText)findViewById(R.id.labor);
        text3=(EditText)findViewById(R.id.fodder);
        text4=(EditText)findViewById(R.id.water);
        text5=(EditText)findViewById(R.id.electricity);
        text6=(EditText)findViewById(R.id.medical);
        text7=(EditText)findViewById(R.id.assest);
        text9=(EditText)findViewById(R.id.other1);
        text10=(EditText)findViewById(R.id.other2);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(expenses.this,admin.class);
                startActivity(intent);
            }
        });
        b= (Button) findViewById(R.id.save);
        b.setOnClickListener(this);
        spinner2= (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter);
        db=openOrCreateDatabase("ADIL",MODE_PRIVATE,null);
        String string="CREATE TABLE IF NOT EXISTS EXPENCES_Dailly (Date VARCHAR,Transport VARCHAR,Labour VARCHAR,Fodder VARCHAR,Water VARCHAR,Electricity VARCHAR,Medical VARCHAR,Asset VARCHAR,Other1 VARCHAR,Other2 VARCHAR,TOTAL VARCHAR);";
        String string1="CREATE TABLE IF NOT EXISTS EXPENCES_Mnthly (Month VARCHAR,Year VARCHAR,Transport VARCHAR,Labour VARCHAR,Fodder VARCHAR,Water VARCHAR,Electricity VARCHAR,Medical VARCHAR,Asset VARCHAR,Other1 VARCHAR,Other2 VARCHAR,TOTAL VARCHAR);";
        String string2="CREATE TABLE IF NOT EXISTS EXPENCES_Yearly (Year VARCHAR,Transport VARCHAR,Labour VARCHAR,Fodder VARCHAR,Water VARCHAR,Electricity VARCHAR,Medical VARCHAR,Asset VARCHAR,Other1 VARCHAR,Other2 VARCHAR,TOTAL VARCHAR);";

        db.execSQL(string);
        db.execSQL(string1);
        db.execSQL(string2);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if(query.equals("EXPENCES_Dailly"))
        {
            date=e.getText().toString();
            e.setText(date);
            contentValues.put("Date",date);
            dialog.show();
        }
        else  if (query.equals("EXPENCES_Mnthly"))
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
        String trans=text1.getText().toString();
        String labor=text2.getText().toString();
        String food=text3.getText().toString();
        String wtr=text4.getText().toString();
        String elect=text5.getText().toString();
        String mdcl=text6.getText().toString();
        String ast=text7.getText().toString();
        String or1=text9.getText().toString();
        String or2=text10.getText().toString();
        int a=Integer.parseInt(trans)+Integer.parseInt(labor)+Integer.parseInt(food)+Integer.parseInt(wtr)+Integer.parseInt(elect)+Integer.parseInt(mdcl)+Integer.parseInt(ast)+Integer.parseInt(or1)+Integer.parseInt(or2);
       String total=String.valueOf(a);
        contentValues.put("Transport",trans);
        contentValues.put("Labour",labor);
        contentValues.put("Fodder",food);
        contentValues.put("Water",wtr);
        contentValues.put("Electricity",elect);
        contentValues.put("Medical",mdcl);
        contentValues.put("Asset",ast);
        contentValues.put("Other1",or1);
        contentValues.put("Other2",or2);
        contentValues.put("TOTAL",total);
       db.insert(query,null,contentValues);
        Toast.makeText(this,"Suceessfully Inserted!!!"+query,Toast.LENGTH_SHORT).show();
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(expenses.this, new DatePickerDialog.OnDateSetListener() {
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
                query="EXPENCES_Dailly";
                break;
            case 2:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.buffalo);
                e1= (EditText) dialog.findViewById(R.id.month);
                e2= (EditText) dialog.findViewById(R.id.year);
                dialog.show();
                query="EXPENCES_Mnthly";
                break;
            case  3:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.year);
                e3= (EditText) dialog.findViewById(R.id.year1);
                dialog.show();
                query="EXPENCES_Yearly";
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
