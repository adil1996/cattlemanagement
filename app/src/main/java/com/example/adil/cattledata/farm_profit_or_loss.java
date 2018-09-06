package com.example.adil.cattledata;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;

public class farm_profit_or_loss extends ActionBarActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{

    TextView textView;
    Button save;
    SQLiteDatabase db;
    String [] item={"","DAILY","MONTHLY","YEARLY"};
    Spinner spinner;
    Dialog dialog;
    String date;
    EditText e,e1,e2,e3;
    boolean flag1=false,flag2=false,flag3=false;
    String select,report="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_profit_or_loss);
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
        db=openOrCreateDatabase("ADIL",MODE_PRIVATE,null);
        textView= (TextView) findViewById(R.id.profit_text);
        save= (Button) findViewById(R.id.profit_button);
        save.setOnClickListener(this);
        spinner= (Spinner) findViewById(R.id.profit_spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
      spinner.setOnItemSelectedListener(this);
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(farm_profit_or_loss.this, new DatePickerDialog.OnDateSetListener() {
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
        Toast.makeText(this,"ASFSDGHDH",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        Cursor c,c1;
        String total = null;
        float semi= (float) 0.0;
        report="            REPORT_CARD    ";

        if(flag1)
        {
            date=e.getText().toString();
            report+="\n    DATE   :  "+date;
            c=db.rawQuery("SELECT * FROM EXPENCES_Dailly",null);
            int l=c.getCount();
            c.moveToFirst();
            while(l>0)
            {
                String s=c.getString(c.getColumnIndex("Date"));
                if(s.equals(date))
                {
                    total=c.getString(c.getColumnIndex("TOTAL"));
                    break;
                }
                c.moveToNext();
                l--;

            }
            int c2=0;
            if(total!=null)
            { int t=Integer.parseInt(total);
            semi=(float)(t/9.0);}
            c1=db.rawQuery("SELECT * FROM DailyIncome",null);
            c1.moveToFirst();
            int l1=c1.getCount();
            int value;
            float v1;
            String a;
            while(l1>0)
            {
                String s1=c1.getString(c1.getColumnIndex("Date"));
                if(s1.equals(date))
                {
                    a=c1.getString(c1.getColumnIndex("Milk"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n    MILK           :  Rs."+v1;
                    a=c1.getString(c1.getColumnIndex("Cattle"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  CATTLE   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Butter"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  BUTTER   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Paneer"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  PANEER   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Curd"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  CURD     :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Oter1"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  OTHER1   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Other2"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  OTHER2   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("TOTAL"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  TOTAL    :  "+v1+"Rs.";
                break;}
                    c1.moveToNext();
                    l1--;


            }
        }
        else if (flag2)
        {
            String month=e1.getText().toString();
            String year=e2.getText().toString();
            report+="\n  MONTH   :"+month+" "+year;
            c=db.rawQuery("SELECT * FROM EXPENCES_Mnthly",null);
            int l=c.getCount();
            c.moveToFirst();
            while(l>0)
            {
                String s=c.getString(c.getColumnIndex("Month"));
                String s3=c.getString(c.getColumnIndex("Year"));
                if(s.equals(month)&&s3.equals(year))
                {
                    total=c.getString(c.getColumnIndex("TOTAL"));
                    break;
                }
                c.moveToNext();
                l--;

            }
            int c2=0;
            if(total!=null)
            { int t=Integer.parseInt(total);
                semi=(float)(t/9.0);}
            c1=db.rawQuery("SELECT * FROM MonthlyIncome",null);
            c1.moveToFirst();
            int l1=c1.getCount();
            int value;
            float v1;
            String a;
            while(l1>0)
            {
                String s1=c1.getString(c1.getColumnIndex("Month"));
                String s2=c1.getString(c1.getColumnIndex("Year"));
                if(s1.equals(month)&&s2.equals(year))
                {
                    a=c1.getString(c1.getColumnIndex("Milk"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  MILK     :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Cattle"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  CATTLE   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Butter"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  BUTTER   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Paneer"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  PANEER   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Curd"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  CURD     :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Oter1"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  OTHER1   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Other2"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  OTHER2   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("TOTAL"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  TOTAL    :  "+v1+"Rs.";
                    break;}
                c1.moveToNext();
                l1--;


            }
        }
        else
        {
            String year=e3.getText().toString();
            report+="\n  YEAR   :"+year;
            c=db.rawQuery("SELECT * FROM EXPENCES_Yearly",null);
            int l=c.getCount();
            c.moveToFirst();
            while(l>0)
            {
                String s3=c.getString(c.getColumnIndex("Year"));
                if(s3.equals(year))
                {
                    total=c.getString(c.getColumnIndex("TOTAL"));
                    break;
                }
                c.moveToNext();
                l--;

            }
            int c2=0;
            if(total!=null)
            { int t=Integer.parseInt(total);
                semi=(float)(t/9.0);}
            c1=db.rawQuery("SELECT * FROM YearlyIncome",null);
            c1.moveToFirst();
            int l1=c1.getCount();
            int value;
            float v1;
            String a;
            while(l1>0)
            {
                String s2=c1.getString(c1.getColumnIndex("Year"));
                if(s2.equals(year))
                {
                    a=c1.getString(c1.getColumnIndex("Milk"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  MILK     :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Cattle"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  CATTLE   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Butter"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  BUTTER   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Paneer"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  PANEER   :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Curd"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  CURD      :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Oter1"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  OTHER1    :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("Other2"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  OTHER2    :  "+v1+"Rs.";
                    a=c1.getString(c1.getColumnIndex("TOTAL"));
                    value=Integer.parseInt(a);
                    v1=value-semi;
                    report+="\n  TOTAL     :  "+v1+"Rs.";
                    break;}
                c1.moveToNext();
                l1--;


            }
        }
        textView.setText(report);}
   }