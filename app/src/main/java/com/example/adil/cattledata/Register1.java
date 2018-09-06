package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register1 extends AppCompatActivity implements View.OnClickListener{
    Button register,submit;
    EditText usern,pass,repass,email,mob,a1,a2;
    SQLiteDatabase db;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        register= (Button) findViewById(R.id.regiteration);
        register.setOnClickListener(this);
        usern= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.password);
        email= (EditText) findViewById(R.id.email);
        repass= (EditText) findViewById(R.id.repassword);
        mob= (EditText) findViewById(R.id.mob_no_);
        db=openOrCreateDatabase("CATTLEDAtA",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS AdminRegister (username VARCHAR,password VARCHAR,email_id VARCHAR,mn VARCHAR);");
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        a1= (EditText) dialog.findViewById(R.id.adminuser);
        a2=(EditText)dialog.findViewById(R.id.adminpwd);
        submit= (Button) dialog.findViewById(R.id.Sbmit);
        submit.setOnClickListener(this);
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
            dialog.show();
            if(v.getId()==R.id.Sbmit) {
                String a3=a1.getText().toString();
                String a4=a2.getText().toString();
                if(!a3.equals("Admin")||!a4.equals("123456"))
                {
                    Toast.makeText(this,"Unathorized -Enter correct admin_details",Toast.LENGTH_SHORT).show();
                    a1.setText("");
                    a2.setText("");
                }
                else {
                    db.execSQL("INSERT INTO AdminRegister VALUES ('" + username + "','" + password + "','" + email1 + "','" + mob1 + "') ;");
                    usern.setText("");
                    pass.setText("");
                    repass.setText("");
                    email.setText("");
                    mob.setText("");
                    a1.setText("");
                    a2.setText("");
                    Intent intent = new Intent(Register1.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "Registered Successfully!!!", Toast.LENGTH_SHORT).show();
            }
                dialog.dismiss();
            }
        }
    }
}
