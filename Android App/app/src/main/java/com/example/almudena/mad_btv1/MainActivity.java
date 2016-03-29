package com.example.almudena.mad_btv1;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    adaptador_byp abp;
    int conteoBYP = 0;
    boolean firstrun = true;
    boolean pendingalarm=false;
    String idpendingalarm="";
    int stpendingalarm=0;
    long tpendingalarm=0;
    Context ctx;
    ListView lista1;
    String changetext;

    int changepos;
    String m_Input;
    renamedialog rendial;
    private int mInterval = 1000;
    private Handler mHandler;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            final convertidoresestado cnv = new convertidoresestado();
            RequestQueue queue = Volley.newRequestQueue(ctx);

            conteoBYP++;
            if (conteoBYP >= abp.getCount()) {
                conteoBYP = 0;
            }
            //String url="http://jsonip.com";
            String url = "http://192.168.2.22:8080/Artik/squery?id=" + abp.getItem(conteoBYP).getIDArtik() + "&s=" + cnv.est_stint(abp.getItem(conteoBYP).getEstado()) + "&m=1";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // tv.setText("Response is: " + response);
                    if (cnv.est_stint(abp.getItem(conteoBYP).getEstado()) == 2 && (Integer.parseInt(response) != 2)) {
                        Notificacion(Integer.parseInt(response));
                    }
                    Nodo_BYP nodo_old = abp.getItem(conteoBYP);
                    abp.insert(new Nodo_BYP(nodo_old.getIDnodo(), cnv.est_intst(Integer.parseInt(response)), nodo_old.getIDArtik(),nodo_old.getAlarmClock()), conteoBYP);
                    abp.remove(nodo_old);
                    //   Toast.makeText(ctx,response,Toast.LENGTH_SHORT).show();

                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {       //             Toast.makeText(ctx,"No hay respuesta",Toast.LENGTH_SHORT).show();

                }
            }
            );
            queue.add(stringRequest);

            mHandler.postDelayed(mStatusChecker, mInterval);
            lista1.refreshDrawableState();
        }

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("");
        ctx = this;
        abp = new adaptador_byp(getApplicationContext());
        if (!(savedInstanceState == null)) {

            ArrayList<Nodo_BYP> nodosalvados = savedInstanceState.getParcelableArrayList("key");
            for (int i = 0; i < nodosalvados.size(); i++) {
                abp.add(nodosalvados.get(i));
            }
            firstrun = false;
        }
        if (intent.hasExtra("salvado") && firstrun) {
            Bundle bandoru = intent.getExtras();
            ArrayList<Nodo_BYP> nodosalvados = bandoru.getParcelableArrayList("salvado");
            for (int i = 0; i < nodosalvados.size(); i++) {
                abp.add(nodosalvados.get(i));
            }
            if (intent.hasExtra("ID")) {
                abp.add(new Nodo_BYP(bandoru.getString("ID"), bandoru.getString("Estado"), bandoru.getString("IDArtik")));
            }
        } else if (!intent.hasExtra("salvado") && firstrun) {

            if (intent.hasExtra("listanodos")) {
                ArrayList<Nodo_BYP> nodosalvados = intent.getParcelableArrayListExtra("listanodos");
                for (int i = 0; i < nodosalvados.size(); i++) {
                    abp.add(nodosalvados.get(i));
                }
            } else {
                abp.add(new Nodo_BYP("Primera Planta", "Caliente", "1"));
                abp.add(new Nodo_BYP("Segunda Planta", "Frío", "2"));
                abp.add(new Nodo_BYP("Tercera Planta", "Recirculando", "3"));
                abp.add(new Nodo_BYP("Planta Cuarta", "Error", "4"));

            }
        }
        lista1 = (ListView) findViewById(R.id.listView);

        lista1.setMinimumHeight(lista1.getHeight());
        lista1.setAdapter(abp);
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == R.id.imageView4)
                    Toast.makeText(ctx,"has acertado a darle, viva",Toast.LENGTH_LONG);
                lista1.showContextMenuForChild(view);
                /*Intent i = new Intent(ctx, Graph.class);
                i.putExtra("posicion", position);
                startActivity(i);*/
            }
        });

        mHandler = new Handler();
        startRepeatingTask();
        registerForContextMenu(lista1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.actions, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        RequestQueue queue = Volley.newRequestQueue(this);
        switch (item.getItemId()) {
            case R.id.cnt_mnu_stats:
                Intent i = new Intent(ctx, Graph.class);
                i.putExtra("posicion", info.position);
                startActivity(i);
                break;
            case R.id.cnt_mnu_alarma:

                abp.getItem(info.position).toggleAlarmClock();
                lista1.refreshDrawableState();
                Log.w("alarma", Settings.System.getString(getContentResolver(), Settings.System.NEXT_ALARM_FORMATTED));

                idpendingalarm=abp.getItem(info.position).getIDArtik();
                stpendingalarm=(abp.getItem(info.position).getAlarmClock())?1:0;
                pendingalarm=true;

                String kek=Settings.System.getString(getContentResolver(), Settings.System.NEXT_ALARM_FORMATTED).replaceAll("\\s+", "");
                StringRequest sPendingAlarm = new StringRequest(Request.Method.GET, "http://192.168.2.22:8080/Artik/alarm?id="+idpendingalarm+"&d="+kek, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("Alarma","works");
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.w("Alarma","not workerino");

                    }
                }
                );
                queue.add(sPendingAlarm);

                Log.w("Alarma",abp.getItem(changepos).getAlarmClock().toString());
                break;
            case R.id.cnt_mnu_recircular:
                // Toast.makeText(this, "Conectando con "+abp.getItem(info.position).getIDnodo() , Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, ""+info.position , Toast.LENGTH_SHORT).show();
                //new Recircular().execute(""+(info.position+1));
                /*new Recircular().execute("" + abp.getItem(info.position).getIDArtik());

                 */
                abp.getItem(info.position).setEstado("Recirculando");
                lista1.refreshDrawableState();
                //Toast.makeText(ctx,testpollas,    Toast.LENGTH_LONG).show();
                break;
            case R.id.cnt_mnu_rename:
                changepos = info.position;
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.renamelayout, null);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setView(view);
                final EditText UserInput = (EditText) view.findViewById(R.id.nnombre);
                alertBuilder.setCancelable(true).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.w("Hasta aquí", "hemos llegau");
                        Nodo_BYP nodo_old = abp.getItem(changepos);
                        abp.insert(new Nodo_BYP(UserInput.getText().toString(), nodo_old.getEstado(), nodo_old.getIDArtik()), changepos);
                        abp.remove(abp.getItem(changepos + 1));
                        lista1.refreshDrawableState();

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.cnt_mnu_eliminar:
                String url = "http://192.168.2.22:8080/Artik/remove?id=" + abp.getItem(info.position).getIDArtik();

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




                /*rendial=new renamedialog();
                rendial.show(getFragmentManager(), "Hello");
                /*if(!rendial.getNombre().equals("%%nullname%%")){
                abp.getItem(info.position).setIDnodo(rendial.getNombre());
            }*/
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {


            Intent in = new Intent(this, Nuevobypass.class);
            ArrayList<Nodo_BYP> listanodos = new ArrayList<Nodo_BYP>();
            for (int i = 0; i < abp.getCount(); i++) {
                listanodos.add(i, abp.getItem(i));
            }
            in.putParcelableArrayListExtra("salvado", listanodos);
            startActivity(in);
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ArrayList<Nodo_BYP> listanodos = new ArrayList<Nodo_BYP>();
        for (int i = 0; i < abp.getCount(); i++) {
            listanodos.add(i, abp.getItem(i));
        }
        outState.putParcelableArrayList("key", listanodos);
        super.onSaveInstanceState(outState);
    }

/*    private class TimeoutOperation extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setSmallIcon(R.drawable.ic_action_reload).setAutoCancel(true).setContentTitle("Recirculación Completa").setContentText("La recirculación se ha completado, ya puedes utilizar el agua caliente sin desperdiciar ni una gota.");
            Intent resultIntent= new Intent(ctx,MainActivity_gv.class);
            TaskStackBuilder stackBuilder=TaskStackBuilder.create(ctx);
            stackBuilder.addParentStack(MainActivity_gv.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(0,mBuilder.build());

            super.onPostExecute(result);
        }
    }*/

    public void Clicky(View v) {
        Intent i = new Intent(this, Graph.class);
        startActivity(i);
    }

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    void Notificacion(int resultado) {
        NotificationCompat.Builder mBuilder;
        if (resultado == 1) {
            mBuilder = new NotificationCompat.Builder(ctx).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setSmallIcon(R.drawable.ic_action_reload).setAutoCancel(true).setContentTitle("Recirculation complete").setContentText("Recirculation is complete, you can now enjoy hot water without wasting a single drop.");
            NotificationCompat.InboxStyle inboxStyle =
                    new NotificationCompat.InboxStyle();
            String[] events = new String[6];
            events[1]="You can now enjoy hot water without";
            events[2]="wasting a single drop.";
            events[3]="\n";
            events[4]="You've saved 3.52 gallons this time.";
// Sets a title for the Inbox in expanded layout
            inboxStyle.setBigContentTitle("Recirculation Complete");
            mBuilder.setStyle(inboxStyle);
// Moves events into the expanded layout
            for (int i=0; i < events.length; i++) {

                inboxStyle.addLine(events[i]);
            }
        } else {
            mBuilder = new NotificationCompat.Builder(ctx).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setSmallIcon(R.drawable.ic_error_24dp).setAutoCancel(true).setContentTitle("Error en la recirculación").setContentText("Ooops! Ha habido un problema mientras se realizaba la recirculación");
        }
        Intent resultIntent = new Intent(ctx, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }

    public boolean saveArray(String[] array, String arrayName, Context mContext, String filename) {
        SharedPreferences prefs = mContext.getSharedPreferences(filename, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public String[] loadArray(String arrayName, Context mContext, String filename) {
        SharedPreferences prefs = mContext.getSharedPreferences(filename, 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

    private class Recircular extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... param) {
            stopRepeatingTask();
            final convertidoresestado cnv = new convertidoresestado();
            RequestQueue queue = Volley.newRequestQueue(ctx);


            //String url="http://jsonip.com";
            //String url = "http://192.168.2.22:8080/Artik/squery?id=".concat(param[0]) + "&s=2&m=1";
            String url = "http://192.168.2.22:8080/Artik/squery?id=1&s=2&m=1";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(ctx,"Iniciando la recirculación",Toast.LENGTH_SHORT).show();
                    Nodo_BYP nodo_old = abp.getItem(conteoBYP);
                    abp.insert(new Nodo_BYP(nodo_old.getIDnodo(), cnv.est_intst(Integer.parseInt(response)), nodo_old.getIDArtik()), conteoBYP);
                    abp.remove(nodo_old);

                    lista1.refreshDrawableState();

                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Toast.makeText(ctx,"No hay respuesta",Toast.LENGTH_SHORT).show();
                }
            }
            );
            queue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startRepeatingTask();
            super.onPostExecute(aVoid);
        }


    }


}


