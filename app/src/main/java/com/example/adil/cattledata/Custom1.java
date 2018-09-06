package com.example.adil.cattledata;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ADIL on 05-03-2017.
 */


public class Custom1 extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<Integer>imgid;
    public Custom1(Activity context, ArrayList<String> itemname, ArrayList<Integer>imgid) {
        super(context, R.layout.grid_vew1, itemname);
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate=context.getLayoutInflater();
        View v=inflate.inflate(R.layout.grid_vew1,null,true);
        TextView t1;
        ImageView iv=(ImageView) v.findViewById(R.id.imageView9);
        t1=(TextView) v.findViewById(R.id.textView7);
        t1.setText(itemname.get(position));
        iv.setImageResource(imgid.get(position));
        return v;
    }
}
