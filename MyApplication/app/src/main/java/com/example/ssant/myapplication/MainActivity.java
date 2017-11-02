package com.example.ssant.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = (Spinner) findViewById(R.id.spinner);
        String[] categorias = {"Categoria 1", "Categoria 2", "Categoria 3", "Categoria 4"};
        sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias));
    }
}
