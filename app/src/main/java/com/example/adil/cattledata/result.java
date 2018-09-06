package com.example.adil.cattledata;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class result extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    Spinner spinner;
    Button b,b1,b2;
    SQLiteDatabase db;
    int c=0;
    String [] item={"","DAILY","MONTHLY","YEARLY"};
    Dialog dialog;
    EditText e,e1,e2,e3;
    boolean flag1=false,flag2=false,flag3=false,flsg4=false;
    String report="";
    String date,month,year;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        spinner= (Spinner) findViewById(R.id.dt_selecter);
        b= (Button) findViewById(R.id.generate_report1);
        b1= (Button) findViewById(R.id.generate_report2);
        b2= (Button) findViewById(R.id.generate_report3);
        b1.setOnClickListener(this);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);
        textView= (TextView) findViewById(R.id.report1);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        db=openOrCreateDatabase("ADIL",MODE_PRIVATE,null);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        report="";
        Cursor cursor;
        flsg4=false;
        int l;
        switch (v.getId())
        {
            case R.id.generate_report1:
               if(flag1)
               {

                   date=e.getText().toString();
                   report+="  DATE   :  "+date;
                   cursor=db.rawQuery("SELECT * FROM Milk_Dailly WHERE Date='"+date+"';",null);
                   cursor.moveToFirst();
                   if(cursor.getCount()>0)
                   {
                       String a= cursor.getString(cursor.getColumnIndex("CA_NO"));
                       report+="\n"+ a;
                       String b=cursor.getString(cursor.getColumnIndex("CA_NAME"));
                       report+="\n"+b;
                       String c=cursor.getString(cursor.getColumnIndex("QUANTITY"));
                       report+="\n"+c;
                   }
                   textView.setText(report);
               }
                else if(flag2)
                {
                    month=e1.getText().toString();
                    year=e2.getText().toString();
                    report+=" Month "+month+" "+year;
                    cursor=db.rawQuery("SELECT * FROM Milk_Monthly WHERE Month = '"+month+"' AND Year = '"+year+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        String a= cursor.getString(cursor.getColumnIndex("CA_NO"));
                        report+="\n"+ a;
                        String b=cursor.getString(cursor.getColumnIndex("CA_NAME"));
                        report+="\n"+b;
                        String c=cursor.getString(cursor.getColumnIndex("QUANTITY"));
                        report+="\n"+c;
                    }
                    textView.setText(report);
                }
                else
               {
                   year=e3.getText().toString();
                   report+=" Year "+year;
                   cursor=db.rawQuery("SELECT * FROM Milk_Yearly WHERE Year = '"+year+"';",null);
                   cursor.moveToFirst();
                   if(cursor.getCount()>0)
                   {
                       String a= cursor.getString(cursor.getColumnIndex("CA_NO"));
                       report+="\n"+ a;
                       String b=cursor.getString(cursor.getColumnIndex("CA_NAME"));
                       report+="\n"+b;
                       String c=cursor.getString(cursor.getColumnIndex("QUANTITY"));
                       report+="\n"+c;
                   }
                   textView.setText(report);
               }
                break;
            case R.id.generate_report2:
                if(flag1)
                {
                    date=e.getText().toString();
                    String a;
                    report+="  DATE   :  "+date;
                    cursor=db.rawQuery("SELECT * FROM DailyIncome WHERE Date='"+date+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        a=cursor.getString(cursor.getColumnIndex("Milk"));
                        report+="\n MILK   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Cattle"));
                        report+="\n CATTLE   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Butter"));
                        report+="\n BUTTER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Paneer"));
                        report+="\n PANEER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Curd"));
                        report+="\n CURD   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Oter1"));
                        report+="\n OTHER1   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other2"));
                        report+="\n OTHER2   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("TOTAL"));
                        report+="\n TOTAL   :  "+a;
                    }
             textView.setText(report);
                }
                else if(flag2)
                {
                    month=e1.getText().toString();
                    year=e2.getText().toString();
                    String a;
                    report+=" Month "+month+" "+year;
                    cursor=db.rawQuery("SELECT * FROM MonthlyIncome WHERE Month = '"+month+"' AND Year = '"+year+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        a=cursor.getString(cursor.getColumnIndex("Milk"));
                        report+="\n MILK   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Cattle"));
                        report+="\n CATTLE   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Butter"));
                        report+="\n BUTTER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Paneer"));
                        report+="\n PANEER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Curd"));
                        report+="\n CURD   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Oter1"));
                        report+="\n OTHER1   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other2"));
                        report+="\n OTHER2   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("TOTAL"));
                        report+="\n TOTAL   :  "+a;
                    }
                    textView.setText(report);
                }
                else
                {
                    year=e3.getText().toString();
                    report+=" Year "+year;
                    String a;
                    cursor=db.rawQuery("SELECT * FROM YearlyIncome WHERE Year = '"+year+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        a=cursor.getString(cursor.getColumnIndex("Milk"));
                        report+="\n MILK   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Cattle"));
                        report+="\n CATTLE   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Butter"));
                        report+="\n BUTTER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Paneer"));
                        report+="\n PANEER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Curd"));
                        report+="\n CURD   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Oter1"));
                        report+="\n OTHER1   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other2"));
                        report+="\n OTHER2   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("TOTAL"));
                        report+="\n TOTAL   :  "+a;
                    }
                    textView.setText(report);
                }
                c=1;
                break;
            case R.id.generate_report3:
                if(flag1)
                {
                    date=e.getText().toString();
                    String a;
                    report+="  DATE   :  "+date;
                    cursor=db.rawQuery("SELECT * FROM EXPENCES_Dailly WHERE Date='"+date+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        a=cursor.getString(cursor.getColumnIndex("Transport"));
                        report+="\n MILK   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Labour"));
                        report+="\n CATTLE   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Fodder"));
                        report+="\n BUTTER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Water"));
                        report+="\n PANEER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Electricity"));
                        report+="\n CURD   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Medical"));
                        report+="\n OTHER1   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Asset"));
                        report+="\n OTHER2   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other1"));
                        report+="\n TOTAL   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other2"));
                        report+="\n TOTAL   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("TOTAL"));
                        report+="\n TOTAL   :  "+a;

                    }
                    textView.setText(report);
                }
                else if (flag2)
                {
                    month=e1.getText().toString();
                    year=e2.getText().toString();
                    String a;
                    report+=" Month "+month+" "+year;
                    cursor=db.rawQuery("SELECT * FROM EXPENCES_Mnthly WHERE Month = '"+month+"' AND Year = '"+year+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        a=cursor.getString(cursor.getColumnIndex("Transport"));
                        report+="\n MILK   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Labour"));
                        report+="\n CATTLE   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Fodder"));
                        report+="\n BUTTER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Water"));
                        report+="\n PANEER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Electricity"));
                        report+="\n CURD   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Medical"));
                        report+="\n OTHER1   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Asset"));
                        report+="\n OTHER2   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other1"));
                        report+="\n TOTAL   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other2"));
                        report+="\n TOTAL   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("TOTAL"));
                        report+="\n TOTAL   :  "+a;

                    }
                    textView.setText(report);
                }
                else {
                    year=e3.getText().toString();
                    report+=" Year "+year;
                    String a;
                    cursor=db.rawQuery("SELECT * FROM EXPENCES_Yearly WHERE Year = '"+year+"';",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0)
                    {
                        a=cursor.getString(cursor.getColumnIndex("Transport"));
                        report+="\n MILK   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Labour"));
                        report+="\n CATTLE   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Fodder"));
                        report+="\n BUTTER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Water"));
                        report+="\n PANEER   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Electricity"));
                        report+="\n CURD   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Medical"));
                        report+="\n OTHER1   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Asset"));
                        report+="\n OTHER2   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other1"));
                        report+="\n TOTAL   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("Other2"));
                        report+="\n TOTAL   :  "+a;
                        a=cursor.getString(cursor.getColumnIndex("TOTAL"));
                        report+="\n TOTAL   :  "+a;

                    }
                    textView.setText(report);
                }
                c=2;
               break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        flag1=false;flag2=false;flag3=false;
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(result.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                e.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });
                flag1=true;
                dialog.show();
                break;
            case 2:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.buffalo);
                e1= (EditText) dialog.findViewById(R.id.month);
                e2= (EditText) dialog.findViewById(R.id.year);
                flag2=true;
                dialog.show();
                break;
            case  3:
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.year);
                e3= (EditText) dialog.findViewById(R.id.year1);
                flag3=true;
                dialog.show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
