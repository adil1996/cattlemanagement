package com.example.adil.cattledata;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{
     Button register;
    EditText usern,pass,repass,email,mob;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register= (Button) findViewById(R.id.regiteration);
        register.setOnClickListener(this);
        usern= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.password);
        email= (EditText) findViewById(R.id.email);
        repass= (EditText) findViewById(R.id.repassword);
        mob= (EditText) findViewById(R.id.mob_no_);
        db=openOrCreateDatabase("CATTLEDAtA",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS UserRegister (username VARCHAR,password VARCHAR,email_id VARCHAR,mn VARCHAR);");
    }

    @Override
    public void onClick(View v) {
        String username=usern.getText().toString();
        String password=pass.getText().toString();
        String email1=email.getText().toString();
        String repassword=repass.getText().toString();
        String mob1=mob.getText().toString();
        String mobilePattern= "[0-9]{10}";
        String emialPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(username.equals("")||password.equals("")||repassword.equals("")||email1.equals("")||mob1.equals(""))
        {
            Toast.makeText(this, "please fill the empty fields", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(repassword))
        {
            Toast.makeText(this, "please enter the correct password", Toast.LENGTH_SHORT).show();
            pass.setText("");
            repass.setText("");
        }
        else if(username.length()>25)
        {
            Toast.makeText(this,"Please enter less than 25 characters in username",Toast.LENGTH_SHORT).show();
            usern.setText("");
        }
        else if(!email1.matches(emialPattern))
        {
            Toast.makeText(this,"please enter valid email_address",Toast.LENGTH_SHORT).show();
            email.setText("");
        }
        else if(!mob1.matches(mobilePattern))
        {
            Toast.makeText(this,"please enter 10 digit valid phone number",Toast.LENGTH_SHORT).show();
            mob.setText("");
        }
        else
        {
            db.execSQL("INSERT INTO UserRegister VALUES ('"+username+"','"+password+"','"+email1+"','"+mob1+"') ;");
            usern.setText("");
            pass.setText("");
            repass.setText("");
            email.setText("");
            mob.setText("");
            Intent intent=new Intent(Register.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"Registered Successfully!!!",Toast.LENGTH_SHORT).show();


        }
    }
}
