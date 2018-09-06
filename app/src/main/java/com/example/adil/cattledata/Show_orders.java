package com.example.adil.cattledata;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class Show_orders extends AppCompatActivity {
    SQLiteDatabase db;
    TextView p_id,P_name,cost,Name,Email,Address,Mo_no,qty;
    ImageView imageView;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);
        p_id= (TextView) findViewById(R.id.p_id);
        P_name= (TextView) findViewById(R.id.p_name);
        cost= (TextView) findViewById(R.id.cost);
        imageView= (ImageView) findViewById(R.id.putImg);
        Name=(TextView) findViewById(R.id.name);
        Email=(TextView) findViewById(R.id.email);
        Mo_no=(TextView) findViewById(R.id.mobile_no);
        Address=(TextView) findViewById(R.id.address);
        qty=(TextView) findViewById(R.id.Qty);
        Intent intent=getIntent();
        val=intent.getStringExtra("first");
        String s="SELECT * FROM P_INFO WHERE P_ID = '"+val+"';";
        db = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        Cursor c;
        c=db.rawQuery(s,null);
        int l=c.getCount();
        c.moveToFirst();
        if (l>0)
        {
            p_id.setText(c.getString(c.getColumnIndex("P_ID")));
            P_name.setText(c.getString(c.getColumnIndex("P_NAME")));
            cost.setText(c.getString(c.getColumnIndex("PRICE")));
            Name.setText(c.getString(c.getColumnIndex("NAME")));
            Email.setText(c.getString(c.getColumnIndex("EMAIL")));
            Address.setText(c.getString(c.getColumnIndex("ADDRESS")));
            qty.setText(c.getString(c.getColumnIndex("QUANTIY")));
            Mo_no.setText(c.getString(c.getColumnIndex("MOBILE_NO")));
            byte[] data=c.getBlob(c.getColumnIndex("Image"));
            ByteArrayInputStream image1=new ByteArrayInputStream(data);
            Bitmap theImage= BitmapFactory.decodeStream(image1);
            imageView.setImageBitmap(theImage);

        }

    }
}
