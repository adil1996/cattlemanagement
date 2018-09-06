package com.example.adil.cattledata;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Dialog dialog;
    Button c_info,p_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.account:
                Intent intent=new Intent(admin.this,My_account.class);
                startActivity(intent);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_information:
                   dialog=new Dialog(this);
                dialog.setContentView(R.layout.choose);
                c_info= (Button) dialog.findViewById(R.id.animal);
                p_info= (Button) dialog.findViewById(R.id.product);
                dialog.show();
                c_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(admin.this,Cattle_information.class);
                        startActivity(intent1);
                        Toast.makeText(getBaseContext(), "moving...", Toast.LENGTH_SHORT).show();
                    }
                });
                p_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(admin.this,Product_information.class);
                        startActivity(intent1);
                        Toast.makeText(getBaseContext(), "moving...", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.c_expences:
                Intent intent2=new Intent(admin.this,expenses.class);
                startActivity(intent2);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_list:
                Intent intent3=new Intent(admin.this,cattle_list.class);
                startActivity(intent3);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.milk_report:
                Intent intent4=new Intent(admin.this,Milk_yield_report.class);
                startActivity(intent4);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.farm_profit:
                Intent intent5=new Intent(admin.this,farm_profit_or_loss.class);
                startActivity(intent5);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Intent intent6=new Intent(admin.this,help.class);
                startActivity(intent6);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.categpry_setting:
                Intent intent7=new Intent(admin.this,estimation.class);
                startActivity(intent7);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_manage:
                Intent intent8=new Intent(admin.this,MainActivity.class);
                startActivity(intent8);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.income:
                Intent intent9=new Intent(admin.this,Income.class);
                startActivity(intent9);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.generate_report:
                Intent intent10=new Intent(admin.this,result.class);
                startActivity(intent10);
                Toast.makeText(this, "moving...", Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
