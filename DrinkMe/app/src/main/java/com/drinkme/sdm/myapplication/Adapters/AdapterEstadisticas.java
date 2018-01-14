package com.drinkme.sdm.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.logic.Estadistico;
import com.drinkme.sdm.myapplication.R;

import java.util.ArrayList;

/**
 * Created by ssant on 01/11/2017.
 */

public class AdapterEstadisticas extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Estadistico> items;


    public AdapterEstadisticas (Activity activity, ArrayList<Estadistico> items) {
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

    public void addAll(ArrayList<Estadistico> estadisticos) {
        for (int i = 0; i<estadisticos.size(); i++) {
            items.add(estadisticos.get(i));
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if(convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.listview_estadisticas, null);
        }

        Estadistico dir = items.get(i);

        TextView nombre = (TextView) view.findViewById(R.id.txNombreTodosLogros);
        nombre.setText(dir.getNombre());

        TextView valor = (TextView) view.findViewById(R.id.txValorEstadistico);


        valor.setText(formateaValor(dir.getValor()));


        return view;
    }

    private String formateaValor(double d) {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
