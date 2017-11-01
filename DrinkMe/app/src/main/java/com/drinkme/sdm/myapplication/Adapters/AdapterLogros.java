package com.drinkme.sdm.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drinkme.sdm.myapplication.Logro;
import com.drinkme.sdm.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ssant on 01/11/2017.
 */

public class AdapterLogros extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Logro> items;


    public AdapterLogros (Activity activity, ArrayList<Logro> items) {
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

    public void addAll(ArrayList<Logro> logros) {
        for (int i = 0; i<logros.size(); i++) {
            items.add(logros.get(i));
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
            view = inf.inflate(R.layout.listview_logros, null);
        }

        Logro dir = items.get(i);

        TextView nombre = (TextView) view.findViewById(R.id.txNombreLogro);
        nombre.setText(dir.getNombre());

        ImageView imagen = (ImageView) view.findViewById(R.id.iconoLogros);
        imagen.setImageDrawable(dir.getImagen());

        return view;
    }
}
