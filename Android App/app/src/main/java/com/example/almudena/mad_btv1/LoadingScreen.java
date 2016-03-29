package com.example.almudena.mad_btv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.22:8080/Artik/nodos";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int n = response.getInt("cantidad");
                    JSONArray IDs = response.getJSONArray("IDArtik");
                    JSONArray Handles = response.getJSONArray("NombreArtik");

                    ArrayList<Nodo_BYP> listanodos = new ArrayList<Nodo_BYP>();
                    for (int i = 0; i < n; i++) {
                        listanodos.add(new Nodo_BYP(Handles.getString(i), "Error", IDs.getString(i)));
                    }
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putParcelableArrayListExtra("listanodos", listanodos);
                    startActivity(i);
                } catch (JSONException e) {
                    Log.w("JSONError", "Error 1: El error de juasón ya llegó.");
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        Ion.with(imgView).placeholder(R.drawable.gifnessito)
                .error(R.drawable.gifnessito)
                .animateGif(AnimateGifMode.ANIMATE)
                .load("http://www.nesswater.com/nesswater/video/GIFNESS.gif");
        queue.add(stringRequest);
    }

    public void Clicky(View v) {
        Intent i = new Intent(getApplicationContext(), MainActivity_gv.class);
        startActivity(i);

    }
}
