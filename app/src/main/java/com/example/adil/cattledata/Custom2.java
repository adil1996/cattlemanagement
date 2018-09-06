package com.example.adil.cattledata;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ADIL on 09-03-2017.
 */

public class Custom2 extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<Bitmap> imgid;
    public Custom2(Activity context,ArrayList<String> itemname,ArrayList<Bitmap> imgid) {
        super(context, R.layout.mylist1, itemname);
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate=context.getLayoutInflater();
        View v=inflate.inflate(R.layout.mylist1,null,true);
        TextView t1;
        ImageView iv=(ImageView) v.findViewById(R.id.list_image);
        t1=(TextView) v.findViewById(R.id.list_text);
        t1.setText(itemname.get(position));
        iv.setImageBitmap(imgid.get(position));
        return v;
    }
}
