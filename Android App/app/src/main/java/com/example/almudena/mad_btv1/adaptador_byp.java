package com.example.almudena.mad_btv1;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Almudena on 24/11/2015.
 */
public class adaptador_byp extends ArrayAdapter<Nodo_BYP> {
    private Context mContext;

    public adaptador_byp(Context c) {
        super(c, 0);
        mContext = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater lif = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = lif.inflate(R.layout.lay_byp_gv, null);
        TextView tv = (TextView) vista.findViewById(R.id.lay_byp_tv);
        tv.setText(getItem(position).getIDnodo());
        ImageView iv= (ImageView) vista.findViewById(R.id.imageView4);

        ImageView ical= (ImageView) vista.findViewById(R.id.imico1);
        ImageView ifri= (ImageView) vista.findViewById(R.id.imico2);
        ImageView irec= (ImageView) vista.findViewById(R.id.imico3);
        ImageView ierr= (ImageView) vista.findViewById(R.id.imico4);
        switch (getItem(position).getIDnodo()){
            case "kitchen 1":
                vista.setBackground(getContext().getDrawable(R.drawable.cocina1));
                break;
            case "kitchen 2":
                vista.setBackground(getContext().getDrawable(R.drawable.cocina2));
                break;
            case "bath 1":
                vista.setBackground(getContext().getDrawable(R.drawable.bano1));
                break;
            case "bath 2":
                vista.setBackground(getContext().getDrawable(R.drawable.bano2));
                break;
            case "bath 3":
                vista.setBackground(getContext().getDrawable(R.drawable.bano3));
                break;
            case "other 1":
                vista.setBackground(getContext().getDrawable(R.drawable.otros1));
                break;
            default:
                vista.setBackground(getContext().getDrawable(R.drawable.otros2));
                break;
        }
        if (getItem(position).getEstado().equals("Caliente")) {
            ical.setImageResource(R.drawable.ic_caliente_on);
            ifri.setImageResource(R.drawable.ic_frio_off);
            irec.setImageResource(R.drawable.ic_recirc_off);
            ierr.setImageResource(R.drawable.ic_error_off);
        } else if (getItem(position).getEstado().equals("Recirculando")) {
            ical.setImageResource(R.drawable.ic_caliente_off);
            ifri.setImageResource(R.drawable.ic_frio_off);
            irec.setImageResource(R.drawable.ic_recirc_on);
            ierr.setImageResource(R.drawable.ic_error_off);
        } else if (getItem(position).getEstado().equals("Error")) {
            ical.setImageResource(R.drawable.ic_caliente_off);
            ifri.setImageResource(R.drawable.ic_frio_off);
            irec.setImageResource(R.drawable.ic_recirc_off);
            ierr.setImageResource(R.drawable.ic_error_on);
        } else {
            ical.setImageResource(R.drawable.ic_caliente_off);
            ifri.setImageResource(R.drawable.ic_frio_on);
            irec.setImageResource(R.drawable.ic_recirc_off);
            ierr.setImageResource(R.drawable.ic_error_off);
        }

        if(getItem(position).getAlarmClock()){
            iv.setImageAlpha(255);
        }
        else{
            iv.setImageAlpha(0);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        vista.setLayoutParams(new ActionBar.LayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, metrics.widthPixels / 2)));
        return vista;
    }


}
