package com.example.adil.cattledata;

import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TabHost;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Tabcheck extends TabActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabcheck);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Admin_register");
        tab1.setIndicator("Admin_register", getResources().getDrawable(R.drawable.a));
        Intent intent = new Intent(Tabcheck.this, Register1.class);
        tab1.setContent(intent);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("User_register");
        tab2.setIndicator("User_register", getResources().getDrawable(R.drawable.b));
        Intent intent1 = new Intent(Tabcheck.this, Register.class);
        tab2.setContent(intent1);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.setCurrentTab(0);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

   // GestureDetectorCompat gd = new GestureDetectorCompat(this, new mygesture());

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this.gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    class mygesture extends GestureDetector.SimpleOnGestureListener {
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getY() > e2.getY()) {
                Intent intent = new Intent(Tabcheck.this, Register1.class);
                startActivity(intent);
            } else if (e1.getY() < e2.getY()) {
                Intent intent1 = new Intent(Tabcheck.this, Register.class);
                startActivity(intent1);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
