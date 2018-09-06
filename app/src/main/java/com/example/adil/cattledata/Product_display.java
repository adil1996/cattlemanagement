package com.example.adil.cattledata;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class Product_display extends AppCompatActivity implements View.OnClickListener {
    TextView cattle_id,breed1,date_of_birth,date_of_purchase,cost;
    SQLiteDatabase db;
    ImageView imageView;
   Button place_o;
    String table,val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        cattle_id= (TextView) findViewById(R.id.cattle_no);
        breed1= (TextView) findViewById(R.id.breed);
        date_of_birth= (TextView) findViewById(R.id.dob);
        date_of_purchase= (TextView) findViewById(R.id.d_0f_purchase);
        cost= (TextView) findViewById(R.id.cost);
        imageView= (ImageView) findViewById(R.id.putImg);
        place_o = (Button) findViewById(R.id.place_order);
        place_o.setOnClickListener(this);

        Intent intent=getIntent();
        table="Product";
        val=intent.getStringExtra("second");
        db = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        Cursor c;
        c=db.rawQuery("SELECT * FROM Product;",null);
        int l=c.getCount();
        c.moveToFirst();
        while (l>0) {

            String s=c.getString(c.getColumnIndex("cattle_no"));
            if(s.equals(val)){
                cattle_id.setText(s);
                breed1.setText(c.getString(c.getColumnIndex("breed")));
                date_of_birth.setText(c.getString(c.getColumnIndex("D_O_B")));
                date_of_purchase.setText(c.getString(c.getColumnIndex("D_O_P")));
                cost.setText(c.getString(c.getColumnIndex("cost")));
                byte[] data=c.getBlob(c.getColumnIndex("Image"));
                ByteArrayInputStream image1=new ByteArrayInputStream(data);
                Bitmap theImage= BitmapFactory.decodeStream(image1);
                imageView.setImageBitmap(theImage);
                break;}
            c.moveToNext();
            l--;

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Product_display.this,place_order.class);
        intent.putExtra("first",table);
        intent.putExtra("second",val);
        startActivity(intent);
    }
}
