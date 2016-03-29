package com.example.almudena.mad_btv1;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class Graph extends AppCompatActivity {
    GraphView graph,graph2,graph3,graph4;
    static final int NUMBER_OF_VIEWS=4;
    TextView tv, tv2;
    ImageView btn1,btn2,btn3;
    SimpleDateFormat spmdf1 = new SimpleDateFormat("yyyy-mm-dd");
    ArrayList<Date> fechas = new ArrayList<Date>();
    ArrayList<Integer> valores = new ArrayList<>();
    ArrayList<Integer> ttvalores=new ArrayList<>();
    ArrayList<Integer> tciclovalores=new ArrayList<>();
    ArrayList<Integer> nciclos=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("");

        TextView op1,op2,op3;
        op1=(TextView)findViewById(R.id.textView4);
        op2=(TextView)findViewById(R.id.textView5);
        op3=(TextView)findViewById(R.id.textView6);
        btn1=(ImageView)findViewById(R.id.imageView9);
        btn2=(ImageView)findViewById(R.id.imageView10);
        btn3=(ImageView)findViewById(R.id.imageView11);

        graph2=(GraphView)findViewById(R.id.graph2);
        graph3=(GraphView)findViewById(R.id.graph3);
        graph4=(GraphView)findViewById(R.id.graph4);
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*graph.setVisibility(View.INVISIBLE);
                graph2.setVisibility(View.VISIBLE);
                graph3.setVisibility(View.INVISIBLE);
                graph4.setVisibility(View.INVISIBLE);*/
                btn1.setBackground(getDrawable(R.drawable.fondo484646));
                btn2.setBackground(getDrawable(R.drawable.fondo5d5a5a));
                btn3.setBackground(getDrawable(R.drawable.fondo5d5a5a));
                graph.setElevation(1);
                graph2.setElevation(2);
                graph3.setElevation(1);
                graph4.setElevation(1);
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.setVisibility(View.INVISIBLE);
                graph2.setVisibility(View.INVISIBLE);
                graph3.setVisibility(View.VISIBLE);
                graph4.setVisibility(View.INVISIBLE);
                btn1.setBackground(getDrawable(R.drawable.fondo5d5a5a));
                btn2.setBackground(getDrawable(R.drawable.fondo484646));
                btn3.setBackground(getDrawable(R.drawable.fondo5d5a5a));
                graph.setElevation(1);
                graph2.setElevation(1);
                graph3.setElevation(2);
                graph4.setElevation(1);
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.setVisibility(View.INVISIBLE);
                graph2.setVisibility(View.INVISIBLE);
                graph3.setVisibility(View.INVISIBLE);
                graph4.setVisibility(View.VISIBLE);
                btn1.setBackground(getDrawable(R.drawable.fondo5d5a5a));
                btn2.setBackground(getDrawable(R.drawable.fondo5d5a5a));
                btn3.setBackground(getDrawable(R.drawable.fondo484646));
                graph.setElevation(1);
                graph2.setElevation(1);
                graph3.setElevation(1);
                graph4.setElevation(2);
            }
        });

       /* tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView3);
        tv.setText("posicion: "+ getIntent().getIntExtra("Inicio", -1));
        tv2.setText("posicion: " + getIntent().getIntExtra("Fin", -1));*/
        btn3.setBackground(getDrawable(R.drawable.fondo484646));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.22:8080/Artik/statsq?id=1";
        JsonObjectRequest JsonRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jvalores = response.getJSONArray("value");
                    JSONArray jttvalores=response.getJSONArray("ttvalue");
                    JSONArray jtciclovalores=response.getJSONArray("tciclovalue");
                    JSONArray jnciclos=response.getJSONArray("nciclos");
                    ArrayList<Date> jdates = new ArrayList<>();
                    fechas = new ArrayList<>();
                    valores = new ArrayList<>();
                    ttvalores= new ArrayList<>();
                    tciclovalores=new ArrayList<>();
                    nciclos=new ArrayList<>();
                    for (int i = 0; i < jvalores.length(); i++) {
                        valores.add(jvalores.getInt(i));
                        ttvalores.add(jttvalores.getInt(i));
                        tciclovalores.add(jtciclovalores.getInt(i));
                        nciclos.add(jnciclos.getInt(i));

                    }
                      /*LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                                new DataPoint(1, valores.get(0)),
                                new DataPoint(2, valores.get(1)),
                                new DataPoint(3, valores.get(2)),
                                new DataPoint(4, valores.get(3)),

                        });*/
                   /* BarGraphSeries<DataPoint> gvalores = new BarGraphSeries<>(new DataPoint[]{
                            new DataPoint((new GregorianCalendar(2015, Calendar.JANUARY, 1)).getTime(), valores.get(0)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.FEBRUARY, 1)).getTime(), valores.get(1)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.MARCH, 1)).getTime(), valores.get(2)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.APRIL, 1)).getTime(), valores.get(3)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.MAY, 1)).getTime(), valores.get(4)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.JUNE, 1)).getTime(), valores.get(5)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.JULY, 1)).getTime(), valores.get(6)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.AUGUST, 1)).getTime(), valores.get(7)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.SEPTEMBER, 1)).getTime(), valores.get(8)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.OCTOBER, 1)).getTime(), valores.get(9)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.NOVEMBER, 1)).getTime(), valores.get(10)),
                            new DataPoint((new GregorianCalendar(2015, Calendar.DECEMBER, 1)).getTime(), valores.get(11))
                    });*/
                    LineGraphSeries<DataPoint> gvalores = new LineGraphSeries<>(new DataPoint[]{
                            new DataPoint(1, valores.get(0)),
                            new DataPoint(2, valores.get(1)),
                            new DataPoint(3, valores.get(2)),
                            new DataPoint(4, valores.get(3)),
                            new DataPoint(5, valores.get(4)),
                            new DataPoint(6, valores.get(5)),
                            new DataPoint(7, valores.get(6)),
                            new DataPoint(8, valores.get(7)),
                            new DataPoint(9, valores.get(8)),
                            new DataPoint(10, valores.get(9)),
                            new DataPoint(11, valores.get(10)),
                            new DataPoint(12, valores.get(11))
                    });
                    LineGraphSeries<DataPoint> gttvalores = new LineGraphSeries<>(new DataPoint[]{
                            new DataPoint(1, ttvalores.get(0)),
                            new DataPoint(2, ttvalores.get(1)),
                            new DataPoint(3, ttvalores.get(2)),
                            new DataPoint(4, ttvalores.get(3)),
                            new DataPoint(5, ttvalores.get(4)),
                            new DataPoint(6, ttvalores.get(5)),
                            new DataPoint(7, ttvalores.get(6)),
                            new DataPoint(8, ttvalores.get(7)),
                            new DataPoint(9, ttvalores.get(8)),
                            new DataPoint(10, ttvalores.get(9)),
                            new DataPoint(11, ttvalores.get(10)),
                            new DataPoint(12, ttvalores.get(11))
                    });
                    LineGraphSeries<DataPoint> gtciclovalores = new LineGraphSeries<>(new DataPoint[]{
                             new DataPoint(1, valores.get(0)),
                             new DataPoint(2, valores.get(1)),
                             new DataPoint(3,  valores.get(2)),
                             new DataPoint(4,  valores.get(3)),
                             new DataPoint(5,  valores.get(4)),
                             new DataPoint(6, valores.get(5)),
                             new DataPoint(7, valores.get(6)),
                             new DataPoint(8, valores.get(7)),
                             new DataPoint(9,  valores.get(8)),
                             new DataPoint(10, valores.get(9)),
                             new DataPoint(11, valores.get(10)),
                             new DataPoint(12, valores.get(11))
                    });
                    LineGraphSeries<DataPoint> gnciclo = new LineGraphSeries<>(new DataPoint[]{
                             new DataPoint(1,   nciclos.get(0)),
                             new DataPoint(2,   nciclos.get(1)),
                             new DataPoint(3,   nciclos.get(2)),
                             new DataPoint(4,   nciclos.get(3)),
                             new DataPoint(5,   nciclos.get(4)),
                             new DataPoint(6,   nciclos.get(5)),
                             new DataPoint(7,   nciclos.get(6)),
                             new DataPoint(8,   nciclos.get(7)),
                             new DataPoint(9,   nciclos.get(8)),
                             new DataPoint(10,  nciclos.get(9)),
                             new DataPoint(11,  nciclos.get(10)),
                             new DataPoint(12,  nciclos.get(11))
                    });
                    gvalores.setColor(Color.GREEN);
                    gttvalores.setColor(Color.GREEN);
                    gtciclovalores.setColor(Color.GREEN);
                    gnciclo.setColor(Color.GREEN);

                    graph.addSeries(gvalores);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(0);
                    graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                    graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                    graph.setBackground(getDrawable(R.drawable.back_graph2));
                    NumberFormat nf=NumberFormat.getInstance();
                    nf.setMinimumIntegerDigits(1);
                    nf.setMaximumFractionDigits(0);
                    graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
                    graph.getViewport().setXAxisBoundsManual(true);
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                    staticLabelsFormatter.setHorizontalLabels(new String[]{"January", "June", "December"});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph.getViewport().setMinX(0.5);
                    graph.getViewport().setMaxX(12.5);
                    graph.getViewport().setMinY(gvalores.getLowestValueY());
                    graph.getViewport().setMaxY(gvalores.getHighestValueY());
                    graph.setVisibility(View.VISIBLE);

                    gvalores.setColor(Color.GREEN);
                    graph2.addSeries(gttvalores);
                    graph2.getGridLabelRenderer().setNumHorizontalLabels(0);
                    graph2.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                    graph2.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                    graph2.setBackground(getDrawable(R.drawable.back_graph2));
                    nf.setMinimumIntegerDigits(1);
                    nf.setMaximumFractionDigits(0);
                    graph2.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
                    graph2.getViewport().setXAxisBoundsManual(true);
                    staticLabelsFormatter = new StaticLabelsFormatter(graph2);
                    staticLabelsFormatter.setHorizontalLabels(new String[]{"January", "June", "December"});
                    graph2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph2.getViewport().setMinX(0.5);
                    graph2.getViewport().setMaxX(12.5);
                    graph2.getViewport().setMinY(gttvalores.getLowestValueY());
                    graph2.getViewport().setMaxY(gttvalores.getHighestValueY());



                    gvalores.setColor(Color.GREEN);
                    graph3.addSeries(gnciclo);
                    graph3.getGridLabelRenderer().setNumHorizontalLabels(0);
                    graph3.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                    graph3.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                    graph3.setBackground(getDrawable(R.drawable.back_graph2));
                    nf.setMinimumIntegerDigits(1);
                    nf.setMaximumFractionDigits(0);
                    graph3.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
                    graph3.getViewport().setXAxisBoundsManual(true);
                    staticLabelsFormatter = new StaticLabelsFormatter(graph3);
                    staticLabelsFormatter.setHorizontalLabels(new String[]{"January", "June", "December"});
                    graph3.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph3.getViewport().setMinX(0.5);
                    graph3.getViewport().setMaxX(12.5);
                    graph3.getViewport().setMinY(gnciclo.getLowestValueY());
                    graph3.getViewport().setMaxY(gnciclo.getHighestValueY());


                    gvalores.setColor(Color.GREEN);
                    graph4.addSeries(gtciclovalores);
                    graph4.getGridLabelRenderer().setNumHorizontalLabels(0);
                    graph4.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
                    graph4.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
                    graph4.setBackground(getDrawable(R.drawable.back_graph2));
                    nf.setMinimumIntegerDigits(1);
                    nf.setMaximumFractionDigits(0);
                    graph4.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
                    graph4.getViewport().setXAxisBoundsManual(true);
                    staticLabelsFormatter = new StaticLabelsFormatter(graph4);
                    staticLabelsFormatter.setHorizontalLabels(new String[]{"January", "June", "December"});
                    graph4.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph4.getViewport().setMinX(0.5);
                    graph4.getViewport().setMaxX(12.5);
                    graph4.getViewport().setMinY(gtciclovalores.getLowestValueY());
                    graph4.getViewport().setMaxY(gtciclovalores.getHighestValueY());




                } catch (org.json.JSONException e) {
                    Log.w("Fallo de jasón", "El jasón falló");
                }
              /*  catch (java.text.ParseException e){
                    Log.w("Fallo de jasón", "El parseo falló");
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("ParseGrafica", "Failed");
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

                        new DataPoint((new GregorianCalendar(2015, Calendar.JANUARY, 1)).getTime(), 400),
                        new DataPoint((new GregorianCalendar(2015, Calendar.FEBRUARY, 1)).getTime(), 700),
                        new DataPoint((new GregorianCalendar(2015, Calendar.MARCH, 1)).getTime(), 600),
                        new DataPoint((new GregorianCalendar(2015, Calendar.APRIL, 1)).getTime(), 600),
                        new DataPoint((new GregorianCalendar(2015, Calendar.MAY, 1)).getTime(), 800),
                        new DataPoint((new GregorianCalendar(2015, Calendar.JUNE, 1)).getTime(), 500),
                        new DataPoint((new GregorianCalendar(2015, Calendar.JULY, 1)).getTime(), 900),
                        new DataPoint((new GregorianCalendar(2015, Calendar.AUGUST, 1)).getTime(), 300),
                        new DataPoint((new GregorianCalendar(2015, Calendar.SEPTEMBER, 1)).getTime(), 400),
                        new DataPoint((new GregorianCalendar(2015, Calendar.OCTOBER, 1)).getTime(), 500),
                        new DataPoint((new GregorianCalendar(2015, Calendar.NOVEMBER, 1)).getTime(), 600),
                        new DataPoint((new GregorianCalendar(2015, Calendar.AUGUST, 1)).getTime(), 800)

                });
                graph.addSeries(series);
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
                graph.getGridLabelRenderer().setNumHorizontalLabels(3);
                //graph.getViewport().setMinX(d1.getTime());
                // graph.getViewport().setMaxX(d5.getTime());
                // graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setScalable(true);
                graph.getViewport().setScrollable(true);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(0);
                graph.getViewport().setMaxY(1200);
                graph.setVisibility(View.VISIBLE);

            }
        });
        queue.add(JsonRequest);

        graph = (GraphView) findViewById(R.id.graph);
        graph.setAlpha(0);
        graph2.setAlpha(0);
        graph3.setAlpha(0);
        graph4.setAlpha(0);
        Date d1 = (new GregorianCalendar(2011, Calendar.AUGUST, 28)).getTime();
        Date d2 = (new GregorianCalendar(2011, Calendar.AUGUST, 29)).getTime();
        Date d3 = (new GregorianCalendar(2011, Calendar.AUGUST, 30)).getTime();
        Date d4 = (new GregorianCalendar(2011, Calendar.AUGUST, 31)).getTime();
        Date d5 = (new GregorianCalendar(2011, Calendar.SEPTEMBER, 1)).getTime();


        new TimeoutOperation().execute("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionnomain, menu);
        return true;
    }

    private class TimeoutOperation extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            graph.setAlpha(1);
            graph2.setAlpha(1);
            graph3.setAlpha(1);
            graph4.setAlpha(1);
            super.onPostExecute(result);
        }
    }
}


