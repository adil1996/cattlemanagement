package com.example.adil.cattledata;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener{
    Button login1;
    EditText usernmae,password;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login1= (Button) findViewById(R.id.SignIn);
        login1.setOnClickListener(this);
        usernmae= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        db=openOrCreateDatabase("CATTLEDAtA",MODE_PRIVATE,null);

    }

    @Override
    public void onClick(View v) {
        String name=usernmae.getText().toString();
        String pass=password.getText().toString();
        if(name.equals("")&&pass.equals(""))
        {
            Toast.makeText(this,"Please fill the empty fields!!!",Toast.LENGTH_SHORT).show();

        }
        else if(name.equals(""))
        {
            Toast.makeText(this,"Please enter the username!!!",Toast.LENGTH_SHORT).show();
        }
        else  if (pass.equals(""))
        {
            Toast.makeText(this,"Please enter the password!!!",Toast.LENGTH_SHORT).show();
        }
        else {
            Cursor c;
            c=db.rawQuery("SELECT * FROM AdminRegister;",null);
            int len=c.getCount();
            c.moveToFirst();
            while(len>0) {
                String name1 = c.getString(c.getColumnIndex("username"));
                String pass1 = c.getString(c.getColumnIndex("password"));
                if(name.equals(name1)&&pass.equals(pass1))
                {
                    Intent intent1=new Intent(login.this,admin.class);
                    usernmae.setText("");
                    password.setText("");
                    startActivity(intent1);
                    break;}
                c.moveToNext();
                len--;
            }
            if(len==0)
            {
                Cursor c1;
                c1=db.rawQuery("SELECT * FROM UserRegister;",null);
                int len1=c1.getCount();
                c1.moveToFirst();
                while(len1>0) {
                    String name1 = c1.getString(c.getColumnIndex("username"));
                    String pass1 = c1.getString(c.getColumnIndex("password"));
                    if(name.equals(name1)&&pass.equals(pass1))
                    {
                        Intent intent1=new Intent(login.this,Main2Activity.class);
                        usernmae.setText("");
                        password.setText("");
                        startActivity(intent1);
                        break;}
                    c1.moveToNext();
                    len1--;
                }
                if(len1==0)
                { Toast.makeText(this,"wrong username/password try again",Toast.LENGTH_LONG).show();
                usernmae.setText("");
                password.setText("");}

        }}
    }
}
