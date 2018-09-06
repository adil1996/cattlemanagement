package com.example.adil.cattledata;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Cattle_information extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
     GridView gridView;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<Integer>integerArrayList=new ArrayList<Integer>();
    EditText cattle_id,breed,date_of_birth,date_of_purchase,cost,weight,fooder,deliveries_no,last_delivery_date,
     vaccanication_no,last_vaccination_date,Incimation_no,last_incimation_date,sale_date,sale_price,date_death;
     DatePickerDialog datePickerDialog;
    SQLiteDatabase db;
    ListView listView;
    Button save,btnSelect;
    ImageView ivImage;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private int flag=0;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_information);
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
         cattle_id= (EditText) findViewById(R.id.cattle_no);
         breed= (EditText) findViewById(R.id.breed);
         date_of_birth= (EditText) findViewById(R.id.dob);
         date_of_birth.setOnClickListener(this);
         date_of_purchase= (EditText) findViewById(R.id.d_0f_purchase);
         date_of_purchase.setOnClickListener(this);
         cost= (EditText) findViewById(R.id.cost);
         weight=(EditText) findViewById(R.id.weight);
         fooder= (EditText) findViewById(R.id.fooder);
         deliveries_no=(EditText) findViewById(R.id.no_of_deliveries);
         last_delivery_date=(EditText) findViewById(R.id.delivery_date);
         last_delivery_date.setOnClickListener(this);
         vaccanication_no=(EditText) findViewById(R.id.no_vaccin);
         last_vaccination_date=(EditText) findViewById(R.id.date_vacc);
         last_vaccination_date.setOnClickListener(this);
         Incimation_no=(EditText) findViewById(R.id.incim);
         last_incimation_date= (EditText) findViewById(R.id.date_inci);
         last_incimation_date.setOnClickListener(this);
         sale_date=(EditText) findViewById(R.id.date_sale);
         sale_date.setOnClickListener(this);
         sale_price=(EditText) findViewById(R.id.sale_price);
         date_death= (EditText) findViewById(R.id.date_death);
         date_death.setOnClickListener(this);
         gridView= (GridView) findViewById(R.id.grid_view1);
        arrayList.add("COW");
        arrayList.add("GOAT");
        arrayList.add("BUFALLO");
        integerArrayList.add(R.drawable.cow1);
        integerArrayList.add(R.drawable.go2);
        integerArrayList.add(R.drawable.buf1);

        Custom1 custom1=new Custom1(this,arrayList,integerArrayList);
         gridView.setAdapter(custom1);
        ivImage= (ImageView) findViewById(R.id.imageView);
        btnSelect= (Button) findViewById(R.id.selectpic);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
         gridView.setOnItemClickListener(this);
         //creating Db
         db=openOrCreateDatabase("ADDINFO",MODE_PRIVATE,null);
         //creating table
      String string="CREATE TABLE IF NOT EXISTS COW(cattle_no VARCHAR,breed VARCHAR,D_O_B VARCHAR,D_O_P VARCHAR,cost VARCHAR,weight VARCHAR,Fooder VARCHAR,no_delivery VARCHAR,delivery_date VARCHAR,no_vacc VARCHAR,Date_vacc VARCHAR,no_inci VARCHAR,Date_inci VARCHAR,D_O_S VARCHAR,C_O_S VARCHAR,D_O_D VARCHAR,Image BLOB);";
        String string2="CREATE TABLE IF NOT EXISTS GOAT(cattle_no VARCHAR,breed VARCHAR,D_O_B VARCHAR,D_O_P VARCHAR,cost VARCHAR,weight VARCHAR,Fooder VARCHAR,no_delivery VARCHAR,delivery_date VARCHAR,no_vacc VARCHAR,Date_vacc VARCHAR,no_inci VARCHAR,Date_inci VARCHAR,D_O_S VARCHAR,C_O_S VARCHAR,D_O_D VARCHAR,Image BLOB);";
         String string3="CREATE TABLE IF NOT EXISTS BUFFALO(cattle_no VARCHAR,breed VARCHAR,D_O_B VARCHAR,D_O_P VARCHAR,cost VARCHAR,weight VARCHAR,Fooder VARCHAR,no_delivery VARCHAR,delivery_date VARCHAR,no_vacc VARCHAR,Date_vacc VARCHAR,no_inci VARCHAR,Date_inci VARCHAR,D_O_S VARCHAR,C_O_S VARCHAR,D_O_D VARCHAR,Image BLOB);";
    //executing query
         db.execSQL(string);
         db.execSQL(string2);
         db.execSQL(string3);
     }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Cattle_information.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(Cattle_information.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
        flag++;
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;


        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int currentBitmapWidth = bm.getWidth();
        int currentBitmapHeight = bm.getHeight();

        int ivWidth = ivImage.getWidth();
        int ivHeight =ivImage.getHeight();

        int newWidth = ivWidth;

        int newHeight = (int) Math.floor((double) currentBitmapHeight *( (double) newWidth / (double) currentBitmapWidth));

        Bitmap newbitMap = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);

        ivImage.setImageBitmap(newbitMap);
        flag++;
    }
    @Override
    public void onClick(final View v) {

        final Calendar c=Calendar.getInstance();
        final int mYear=c.get(Calendar.YEAR);
        final int mMonth=c.get(Calendar.MONTH);
        final int mDay=c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(Cattle_information.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                switch (v.getId())
                {
                    case R.id.dob:
                    date_of_birth .setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case R.id.d_0f_purchase:
                        date_of_purchase.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case R.id.delivery_date:
                        last_delivery_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case R.id.date_vacc:
                        last_vaccination_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case R.id.date_inci:
                        last_incimation_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case R.id.date_sale:
                        sale_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                    case R.id.date_death:
                        date_death.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        break;
                }
            }
        },mYear,mMonth,mDay);
        datePickerDialog.show();
    }

    @Override
   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cattle_no1=cattle_id.getText().toString();
        String breed1=breed.getText().toString();
        String D_O_Birth=date_of_birth.getText().toString();
        String D_O_Pur=date_of_purchase.getText().toString();
        String price=cost.getText().toString();
        String wght=weight.getText().toString();
        String food=fooder.getText().toString();
        String deliv=deliveries_no.getText().toString();
        String last_deliv_date=last_delivery_date.getText().toString();
        String vacc_no=vaccanication_no.getText().toString();
        String last_vacc_date=last_vaccination_date.getText().toString();
        String inci_n=Incimation_no.getText().toString();
        String last_inci_date=last_incimation_date.getText().toString();
        String D_O_Sale=sale_date.getText().toString();
        String Sprice=sale_price.getText().toString();
        String D_o_death=date_death.getText().toString();
        Bitmap bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] pic = baos.toByteArray();
        if(cattle_no1.equals("")||breed1.equals("")||D_O_Birth.equals("")||D_O_Pur.equals("")||price.equals("")||wght.equals("")||food.equals("")||deliv.equals("")||last_deliv_date.equals("")||vacc_no.equals("")||last_vacc_date.equals("")||inci_n.equals("")||last_inci_date.equals(""))
        {
            Toast.makeText(this,"PLEASE FILL THE EMPTY FIELDS!!!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ContentValues cv=new ContentValues();
            cv.put("cattle_no",cattle_no1);
            cv.put("breed",breed1);
            cv.put("D_O_B",D_O_Birth);
            cv.put("D_O_P",D_O_Pur);
            cv.put("cost",price);
            cv.put("weight",wght);
            cv.put("Fooder",food);
            cv.put("no_delivery",deliv);
            cv.put("delivery_date",last_deliv_date);
            cv.put("no_vacc",vacc_no);
            cv.put("Date_vacc",last_vacc_date);
            cv.put("no_inci",inci_n);
            cv.put("Date_inci",last_inci_date);
            cv.put("D_O_S",D_O_Sale);
            cv.put("C_O_S",Sprice);
            cv.put("D_O_D",D_o_death);
            cv.put("Image",pic);
            switch (position){
                case 0:
                    db.insert("COW",null,cv);
                    Toast.makeText(this,"SUCCESSFULLY INSERTED IN COW TABLE",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    db.insert("GOAT",null,cv);
                    Toast.makeText(this,"SUCCESSFULLY INSERTED IN GOAT TABLE",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    db.insert("BUFFALO",null,cv);
                    Toast.makeText(this,"SUCCESSFULLY INSERTED IN BUFFALO TABLE",Toast.LENGTH_SHORT).show();
                    break;
            }
            Intent intent=new Intent(Cattle_information.this,admin.class);
            startActivity(intent);
        }
    }
}
