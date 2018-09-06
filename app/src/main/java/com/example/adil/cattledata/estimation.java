package com.example.adil.cattledata;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class estimation extends AppCompatActivity implements View.OnClickListener {
    Button b, b1;
    DatePickerDialog datePickerDialog;
    EditText editText, e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimation);
        editText = (EditText) findViewById(R.id.dp);
        b = (Button) findViewById(R.id.e_expence);
        b1 = (Button) findViewById(R.id.e_income);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR);
                final int mMonth = c.get(Calendar.MONTH);
                final int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(estimation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.e_expence:
                Intent intent=new Intent(estimation.this,Graph.class);
                String a="exp";
                intent.putExtra("first",a);
                String date=editText.getText().toString();
                intent.putExtra("second",date);
                startActivity(intent);
                break;
            case R.id.e_income:
                Intent intent1=new Intent(estimation.this,Graph.class);
                String a1="inc";
                intent1.putExtra("first",a1);
                String date1=editText.getText().toString();
                intent1.putExtra("second",date1);
                startActivity(intent1);
                break;
        }
    }
}