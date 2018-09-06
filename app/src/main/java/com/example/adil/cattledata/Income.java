package com.example.adil.cattledata;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
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

import static android.R.*;
import static com.example.adil.cattledata.R.id.breed;
import static com.example.adil.cattledata.R.id.date_death;

public class Income extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    EditText milk_income,cattle_income,butter_income,paneer_income,curd_income,oter1,other2,e,e1,e2,e3;
     Spinner spinner1;
    String [] item={"","DAILY","MONTHLY","YEARLY"};
    SQLiteDatabase db;
    Dialog dialog;
    ContentValues contentValues=new ContentValues();
    Button button;
    String query,date;
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
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

      milk_income= (EditText) findViewById(R.id.milk_income);
        cattle_income= (EditText) findViewById(R.id.cattle_sale);
        butter_income= (EditText) findViewById(R.id.b_c_income);
        paneer_income= (EditText) findViewById(R.id.paneer_income);
        curd_income= (EditText) findViewById(R.id.curd_income);
        oter1= (EditText) findViewById(R.id.other_income1);
        other2= (EditText) findViewById(R.id.other_income2);
         button= (Button) findViewById(R.id.saveIncome);
        spinner1= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        String s="CREATE TABLE IF NOT EXISTS DailyIncome(Date VARCHAR,Milk VARCHAR,Cattle VARCHAR,Butter VARCHAR,Paneer VARCHAR,Curd VARCHAR,Oter1 VARCHAR,Other2 VARCHAR,TOTAL VARCHAR);";
        String s1="CREATE TABLE IF NOT EXISTS MonthlyIncome(Month VARCHAR,Year VARCHAR,Milk VARCHAR,Cattle VARCHAR,Butter VARCHAR,Paneer VARCHAR,Curd VARCHAR,Oter1 VARCHAR,Other2 VARCHAR,TOTAL VARCHAR);";
        String s2="CREATE TABLE IF NOT EXISTS YearlyIncome(Year Varchar,Milk VARCHAR,Cattle VARCHAR,Butter VARCHAR,Paneer VARCHAR,Curd VARCHAR,Oter1 VARCHAR,Other2 VARCHAR,TOTAL VARCHAR);";
        db=openOrCreateDatabase("ADIL",MODE_PRIVATE,null);
        db.execSQL(s);
        db.execSQL(s1);
        db.execSQL(s2);
          button.setOnClickListener(this);
        spinner1.setOnItemSelectedListener(this);
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Income.this, new DatePickerDialog.OnDateSetListener() {
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
               query="DailyIncome";
                break;
            case 2:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.buffalo);
                e1= (EditText) dialog.findViewById(R.id.month);
                e2= (EditText) dialog.findViewById(R.id.year);
                dialog.show();
                query="MonthlyIncome";
                break;
            case  3:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.year);
                e3= (EditText) dialog.findViewById(R.id.year1);
                dialog.show();
                query="YearlyIncome";
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"ASFSDGHDH",Toast.LENGTH_LONG).show();
        }

    @Override
    public void onClick(View v) {
        if(query.equals("DailyIncome"))
        {
            date=e.getText().toString();
            e.setText(date);
            contentValues.put("Date",date);
            dialog.show();
        }
        else  if (query.equals("MonthlyIncome"))
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
        String m_Income=milk_income.getText().toString();
        String C_Income=cattle_income.getText().toString();
        String B_Income=butter_income.getText().toString();
        String P_Income=paneer_income.getText().toString();
        String Cu_Income=curd_income.getText().toString();
        String O1_Income=oter1.getText().toString();
        String O2_Income=other2.getText().toString();
        int a=Integer.parseInt(m_Income)+Integer.parseInt(C_Income)+Integer.parseInt(B_Income)+Integer.parseInt(P_Income)+Integer.parseInt(Cu_Income)+Integer.parseInt(O1_Income)+Integer.parseInt(O2_Income);
        String total=String.valueOf(a);
        contentValues.put("Milk",m_Income);
        contentValues.put("Cattle",C_Income);
        contentValues.put("Butter",B_Income);
        contentValues.put("Paneer",P_Income);
        contentValues.put("Curd",Cu_Income);
        contentValues.put("Oter1",O1_Income);
        contentValues.put("Other2",O2_Income);
        contentValues.put("TOTAL",total);
        db.insert(query,null,contentValues);
        Toast.makeText(this,"Successfully Inserted in"+query+"!!!",Toast.LENGTH_SHORT).show();
    }
}
