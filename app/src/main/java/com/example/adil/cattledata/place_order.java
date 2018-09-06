package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class place_order extends AppCompatActivity implements View.OnClickListener{
    String val1,val2;
    EditText Name,Email,Address,Mo_no,qty;
    ImageView imageView;
    TextView p_id,P_name,cost;
    SQLiteDatabase db;
    Button co_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        Intent intent=getIntent();
        val1=intent.getStringExtra("first");
        val2=intent.getStringExtra("second");
        p_id= (TextView) findViewById(R.id.p_id);
        P_name= (TextView) findViewById(R.id.p_name);
        cost= (TextView) findViewById(R.id.cost);
        imageView= (ImageView) findViewById(R.id.putImg);
        Name=(EditText) findViewById(R.id.name);
        Email=(EditText) findViewById(R.id.email);
        Mo_no=(EditText) findViewById(R.id.mobile_no);
        Address=(EditText) findViewById(R.id.address);
        qty=(EditText) findViewById(R.id.Qty);
        String s="SELECT * FROM "+val1+";";
        db = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        Cursor c;
        c=db.rawQuery(s,null);
        int l=c.getCount();
        c.moveToFirst();
        while (l>0) {

            String s1=c.getString(c.getColumnIndex("cattle_no"));
            if(s1.equals(val2)){
                p_id.setText(s1);
                P_name.setText(c.getString(c.getColumnIndex("breed")));
                cost.setText(c.getString(c.getColumnIndex("cost")));
                byte[] data=c.getBlob(c.getColumnIndex("Image"));
                ByteArrayInputStream image1=new ByteArrayInputStream(data);
                Bitmap theImage= BitmapFactory.decodeStream(image1);
                imageView.setImageBitmap(theImage);
                break;}
            c.moveToNext();
            l--;
        }
        String s2="CREATE TABLE IF NOT EXISTS P_INFO(P_ID VARCHAR,P_NAME VARCHAR,PRICE VARCHAR,NAME VARCHAR,EMAIL VARCHAR,ADDRESS VARCHAR,QUANTIY VARCHAR,MOBILE_NO VARCHAR,Image BLOB);";
        db.execSQL(s2);
        co_save= (Button) findViewById(R.id.confinrm_save);
        co_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues=new ContentValues();
        String p_name=P_name.getText().toString();
        String p_id1=p_id.getText().toString();
        String name=Name.getText().toString();
        String address=Address.getText().toString();
        String email=Email.getText().toString();
        String quantity=qty.getText().toString();
        int a=Integer.parseInt(quantity);
        String price=cost.getText().toString();
        String mo=Mo_no.getText().toString();
        int price1=Integer.parseInt(price)*a;
        String t=String.valueOf(price1);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] pic = baos.toByteArray();
        contentValues.put("P_ID",p_id1);
        contentValues.put("P_NAME",p_name);
        contentValues.put("PRICE",price1);
        contentValues.put("NAME",name);
        contentValues.put("EMAIL",email);
        contentValues.put("ADDRESS",address);
        contentValues.put("QUANTIY",quantity);
        contentValues.put("MOBILE_NO",mo);
        contentValues.put("Image",pic);
        db.insert("P_INFO",null,contentValues);
        Toast.makeText(this,"YOUR ORDER IS SUCCESSFULLY SAVED!!!",Toast.LENGTH_SHORT).show();
    }
}
