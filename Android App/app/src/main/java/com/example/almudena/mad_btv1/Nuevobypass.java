package com.example.almudena.mad_btv1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Nuevobypass extends AppCompatActivity {
    EditText Identificador, Estado;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevobypass);
        Identificador = (EditText) findViewById(R.id.editText);
        Estado = (EditText) findViewById(R.id.editText2);
        ctx = getApplicationContext();


    }

    public void Clicky(View v) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.22:8080/Artik/add?name=" + Identificador.getText().toString() + "&num=" + Estado.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent i = new Intent(ctx, LoadingScreen.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
}