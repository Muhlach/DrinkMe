package com.drinkme.sdm.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.R;
import com.drinkme.sdm.myapplication.logic.Logro;

import java.util.ArrayList;

/**
 * Created by ssant on 14/01/2018.
 */

public class AdapterSuperarLogros extends BaseAdapter {

    protected Activity activity;
    protected String[] items;
    protected Drawable img;


    public AdapterSuperarLogros (Activity activity, String[] items, Drawable img) {
        this.activity = activity;
        this.items = items;
        this.img = img;
    }

    @Override
    public int getCount() {
        return items.length;
    }


    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.listview_logro_superado, null);
        }

        String[] text = items[i].split("-");

        TextView t = view.findViewById(R.id.lw_logrosuperado);
        t.setText(text[0].trim());

        TextView t1 = view.findViewById(R.id.lw_puntos);
        t1.setText(text[1].trim());

        return view;
    }
}
