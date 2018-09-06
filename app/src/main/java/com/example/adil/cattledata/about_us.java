package com.example.adil.cattledata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class about_us extends AppCompatActivity {
GridView gridView;
    ArrayList<String>arrayList=new ArrayList<String>();
    ArrayList<Integer>integerArrayList=new ArrayList<Integer>();
   // String [] s={"S.M.Adil","Anil Kumar","Manish Kumar" ,"Shailjakant"};
    //Integer[] id={R.drawable.adil,R.drawable.ani,R.drawable.manish,R.drawable.shailja};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        arrayList.add("Saiyyad Mohammad ADIL\n (Coding and Debugging)");
        arrayList.add("ANIL KUMAR\n (Testing)");
        arrayList.add("MANISH KUMAR \n (Database Connectivity)");
        arrayList.add("SHAILJAKANT \n UI Design");
        integerArrayList.add(R.drawable.ad);
        integerArrayList.add(R.drawable.an);
        integerArrayList.add(R.drawable.mn);
        integerArrayList.add(R.drawable.shail);
    gridView= (GridView) findViewById(R.id.grid_view2);
      Custom1 custom1=new Custom1(this,arrayList,integerArrayList);
        gridView.setAdapter(custom1);
    }
}
