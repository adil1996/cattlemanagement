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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import static com.example.adil.cattledata.R.id.cost;
import static com.example.adil.cattledata.R.id.putImg;

public class ShowData extends AppCompatActivity implements View.OnClickListener{
    Bitmap[] a=new Bitmap[100];
    TextView cattle_id,breed1,date_of_birth,date_of_purchase,cost,weight,fooder,deliveries_no,last_delivery_date,vaccanication_no,last_vaccination_date,
    Incimation_no,last_incimation_date,sale_date,sale_price,date_death;
    SQLiteDatabase db;
    ImageView imageView;
    Button place_o;
    Bitmap theImage;
    String table,val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        cattle_id= (TextView) findViewById(R.id.cattle_no);
        breed1= (TextView) findViewById(R.id.breed);
        date_of_birth= (TextView) findViewById(R.id.dob);
        date_of_purchase= (TextView) findViewById(R.id.d_0f_purchase);
        cost= (TextView) findViewById(R.id.cost);
        weight=(TextView) findViewById(R.id.weight);
        fooder= (TextView) findViewById(R.id.fooder);
        deliveries_no=(TextView) findViewById(R.id.no_of_deliveries);
        last_delivery_date=(TextView) findViewById(R.id.delivery_date);
        vaccanication_no=(TextView) findViewById(R.id.no_vaccin);
        last_vaccination_date=(TextView) findViewById(R.id.date_vacc);
        Incimation_no=(TextView) findViewById(R.id.incim);
        last_incimation_date= (TextView) findViewById(R.id.date_inci);
        sale_date=(TextView) findViewById(R.id.date_sale);
        sale_price=(TextView) findViewById(R.id.sale_price);
        date_death= (TextView) findViewById(R.id.date_death);
        imageView= (ImageView) findViewById(R.id.putImg);
        place_o = (Button) findViewById(R.id.place_order);
        place_o.setOnClickListener(this);
        db = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        Intent intent=getIntent();
        table=intent.getStringExtra("first");
        val=intent.getStringExtra("second");
      Cursor c;
        if(table.equals("COW"))
        c=db.rawQuery("SELECT * FROM COW;",null);
        else if(table.equals("GOAT"))
            c=db.rawQuery("SELECT * FROM GOAT;",null);
        else
            c=db.rawQuery("SELECT * FROM BUFFALO;",null);

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
            weight.setText(c.getString(c.getColumnIndex("weight")));
            fooder.setText(c.getString(c.getColumnIndex("Fooder")));
            last_delivery_date.setText(c.getString(c.getColumnIndex("delivery_date")));
            deliveries_no.setText(c.getString(c.getColumnIndex("no_delivery")));
            vaccanication_no.setText(c.getString(c.getColumnIndex("no_vacc")));
            last_vaccination_date.setText(c.getString(c.getColumnIndex("Date_vacc")));
            Incimation_no.setText(c.getString(c.getColumnIndex("no_inci")));
            last_incimation_date.setText(c.getString(c.getColumnIndex("Date_inci")));
            byte[] data=c.getBlob(c.getColumnIndex("Image"));
            ByteArrayInputStream image1=new ByteArrayInputStream(data);
            theImage= BitmapFactory.decodeStream(image1);
            imageView.setImageBitmap(theImage);
            break;}
        c.moveToNext();
        l--;
    }

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ShowData.this,place_order.class);
        intent.putExtra("first",table);
        intent.putExtra("second",val);
        startActivity(intent);

    }
}

