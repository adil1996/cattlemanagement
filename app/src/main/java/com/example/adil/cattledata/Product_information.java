package com.example.adil.cattledata;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Product_information extends AppCompatActivity implements View.OnClickListener {
    EditText cattle_id, breed, date_of_birth, date_of_purchase, cost;
    ImageView ivImage;
    Button btnSelect, btnSave;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private int flag = 0;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
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
        ivImage = (ImageView) findViewById(R.id.imageView);
        btnSelect = (Button) findViewById(R.id.selectpic);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        cattle_id = (EditText) findViewById(R.id.cattle_no);
        breed = (EditText) findViewById(R.id.breed);
        date_of_birth = (EditText) findViewById(R.id.dob);
        date_of_purchase = (EditText) findViewById(R.id.d_0f_purchase);
        cost = (EditText) findViewById(R.id.cost);
        String string = "CREATE TABLE IF NOT EXISTS Product(cattle_no VARCHAR,breed VARCHAR,D_O_B VARCHAR,D_O_P VARCHAR,cost VARCHAR,Image BLOB);";
        db = openOrCreateDatabase("ADDINFO", MODE_PRIVATE, null);
        db.execSQL(string);
        btnSave = (Button) findViewById(R.id.save_p);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Product_information.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Product_information.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
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

        Bitmap bm = null;


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
        int ivHeight = ivImage.getHeight();

        int newWidth = ivWidth;

        int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) newWidth / (double) currentBitmapWidth));

        Bitmap newbitMap = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);

        ivImage.setImageBitmap(newbitMap);
        flag++;
    }

    @Override
    public void onClick(View v) {
        String cattle_no1 = cattle_id.getText().toString();
        String breed1 = breed.getText().toString();
        String D_O_Birth = date_of_birth.getText().toString();
        String D_O_Pur = date_of_purchase.getText().toString();
        String price = cost.getText().toString();
        Bitmap bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] pic = baos.toByteArray();
        if (cattle_no1.equals("") || breed1.equals("") || D_O_Birth.equals("") || D_O_Pur.equals("") || price.equals("")) {
            Toast.makeText(this, "PLEASE FILL THE EMPTY FIELDS!!!", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues cv = new ContentValues();
            cv.put("cattle_no", cattle_no1);
            cv.put("breed", breed1);
            cv.put("D_O_B", D_O_Birth);
            cv.put("D_O_P", D_O_Pur);
            cv.put("cost", price);
            cv.put("Image",pic);
            db.insert("Product",null,cv);
            Toast.makeText(this,"PRODUCT ADDED SUCCESSFULLY!!!",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Product_information.this,admin.class);
            startActivity(intent);

        }
    }
}