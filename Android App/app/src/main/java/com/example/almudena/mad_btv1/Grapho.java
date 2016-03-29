package com.example.almudena.mad_btv1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class Grapho extends AppCompatActivity {

    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };

    /*final ArrayList<String> Months=new ArrayList<String>() {{
        add("Enero");
        add("Febrero");
        add("Marzo");
        add("Abril");
        add("Mayo");
        add("Junio");
        add("Julio");
        add("Agosto");
        add("Septiembre");
        add("Octubre");
        add("Noviembre");
        add("Diciembre");
    }};*/
    Spinner inicio, fin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapho);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inicio=(Spinner)findViewById(R.id.spinner);
        fin=(Spinner)findViewById(R.id.spinner2);
        inicio.setAdapter(adapter);
        fin.setAdapter(adapter);
        Button Gobutton=(Button)findViewById(R.id.button);
        Gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent i=new Intent(getApplicationContext(),Graph.class);
                i.putExtra("Inicio",inicio.getSelectedItemPosition());
                i.putExtra("Fin",fin.getSelectedItemPosition());
                startActivity(i);
            }
        });
      /*
        inicio.setAdapter(Months);
        fin.setAdapter(Months);*/
    }
}
