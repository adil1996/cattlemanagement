package com.example.adil.cattledata;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class Graph extends ActionBarActivity {
    Intent intent;
    String a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        intent=getIntent();
        a=intent.getStringExtra("first");
        b=intent.getStringExtra("second");
         if(a.equals("inc"))
             c="INCOME";
        else
             c="EXPENCES";
        //BarChart chart = (BarChart) findViewById(R.id.chart);
        LineChart chart= (LineChart) findViewById(R.id.chart);
        ArrayList<String> xVals =getXAxisValues();
        ArrayList<Entry> yVals =setYAxisValues();
        LineDataSet set1;
        set1 = new LineDataSet(yVals, c);
        set1.setFillAlpha(110);
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1);
        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);
        chart.setDescription(b);
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }

    /* private ArrayList<LineDataSet> getDataSet() {
          ArrayList<Entry> valueSet1 = new ArrayList<>();
          BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
          valueSet1.add(v1e1);
          BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
          valueSet1.add(v1e2);
          BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
          valueSet1.add(v1e3);
          BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
          valueSet1.add(v1e4);
          BarEntry v1e5 = new BarEntry(90.000f, 4); // May
          valueSet1.add(v1e5);
          BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
          valueSet1.add(v1e6);
          BarEntry v1e7 = new BarEntry(120.000f, 5); // Jun
          valueSet1.add(v1e7);

         /* ArrayList<BarEntry> valueSet2 = new ArrayList<>();
          BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
          valueSet2.add(v2e1);
          BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
          valueSet2.add(v2e2);
          BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
          valueSet2.add(v2e3);
          BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
          valueSet2.add(v2e4);
          BarEntry v2e5 = new BarEntry(20.000f, 4); // May
          valueSet2.add(v2e5);
          BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
          valueSet2.add(v2e6);8/

          LineDataSet barDataSet1 = new LineDataSet(valueSet1, "Brand 1");
          barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
          dataSets = new ArrayList<>();
          dataSets.add(barDataSet1);
          return dataSets;
      }
*/
    private ArrayList<String> getXAxisValues() {
               ArrayList<String> xAxis = new ArrayList<>();
      if(a.equals("inc")){
        xAxis.add("MILK");
        xAxis.add("CURD");
        xAxis.add("CATTLE");
        xAxis.add("PANEER");
        xAxis.add("BUTTER");
        xAxis.add("OTHER1");
        xAxis.add("OTHER2");
        xAxis.add("TOTAL");}
       else
        {
            xAxis.add("TRANSPORT");
            xAxis.add("LABOUR");
            xAxis.add("FOODER");
            xAxis.add("WATER");
            xAxis.add("ELECTRICITY");
            xAxis.add("MEDICAL");
            xAxis.add("ASSEST");
            xAxis.add("OTHER1");
            xAxis.add("OTHER2");
            xAxis.add("TOTAL");}

        return xAxis;
    }
    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        //String a=intent.getStringExtra("first");
     if(a.equals("inc")){
        SQLiteDatabase db = openOrCreateDatabase("ADIL", MODE_PRIVATE, null);
        Cursor c=db.rawQuery("SELECT * FROM DailyIncome WHERE Date='"+b+"';",null);
        c.moveToFirst();
        if(c.getCount()>0)
        {
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Milk"))), 0));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Curd"))), 1));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Cattle"))), 2));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Paneer"))), 3));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Butter"))), 4));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Oter1"))), 5));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Other2"))), 6));
            yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("TOTAL"))), 7));

        }else
        {
            yVals.add(new Entry(0, 0));
            yVals.add(new Entry(0, 1));
            yVals.add(new Entry(0, 2));
            yVals.add(new Entry(0, 3));
            yVals.add(new Entry(0, 4));
            yVals.add(new Entry(0, 5));
            yVals.add(new Entry(0, 6));
            yVals.add(new Entry(0, 7));

        }
       }
        else
     {
         SQLiteDatabase db = openOrCreateDatabase("ADIL", MODE_PRIVATE, null);
         Cursor c=db.rawQuery("SELECT * FROM EXPENCES_Dailly WHERE Date='"+b+"';",null);
         c.moveToFirst();
         if(c.getCount()>0)
         {
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Transport"))), 0));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Labour"))), 1));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Fodder"))), 2));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Water"))), 3));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Electricity"))), 4));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Medical"))), 5));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Asset"))), 6));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Other1"))), 7));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("Other2"))), 8));
             yVals.add(new Entry(Integer.parseInt(c.getString(c.getColumnIndex("TOTAL"))), 9));

         }else
         {
             yVals.add(new Entry(0, 0));
             yVals.add(new Entry(0, 1));
             yVals.add(new Entry(0, 2));
             yVals.add(new Entry(0, 3));
             yVals.add(new Entry(0, 4));
             yVals.add(new Entry(0, 5));
             yVals.add(new Entry(0, 6));
             yVals.add(new Entry(0, 7));
             yVals.add(new Entry(0, 8));
             yVals.add(new Entry(0, 9));

         }


     }
       /* else
        {
       /* ArrayList<Entry> yVals = new ArrayList<Entry>();
            yVals.add(new Entry(160, 0));
            yVals.add(new Entry(-48, 1));
            yVals.add(new Entry(170.5f, 2));
            yVals.add(new Entry(100, 3));
            yVals.add(new Entry(80.9f, 4));
            yVals.add(new Entry(130, 5));
            yVals.add(new Entry(60.9f, 6));
        //}*/
        return yVals;
    }
}

