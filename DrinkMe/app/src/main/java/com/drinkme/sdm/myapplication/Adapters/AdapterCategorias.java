package com.drinkme.sdm.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.logic.CategoriaBin;
import com.drinkme.sdm.myapplication.R;

import java.util.ArrayList;

/**
 * Created by ssant on 01/11/2017.
 */

public class AdapterCategorias extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<CategoriaBin> items;

    public AdapterCategorias (Activity activity, ArrayList<CategoriaBin> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<CategoriaBin> categorias) {
        for (int i = 0; i<categorias.size(); i++) {
            items.add(categorias.get(i));
        }
    }


    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.listview_categorias, null);
        }

        CategoriaBin dir = items.get(i);
        TextView titulo = (TextView) view.findViewById(R.id.nombreCategoria);
        titulo.setText(dir.getCatName());

        ImageView imagen = (ImageView) view.findViewById(R.id.imgCategoria);
        imagen.setImageDrawable(dir.getCatImg());

        return view;
    }
}
