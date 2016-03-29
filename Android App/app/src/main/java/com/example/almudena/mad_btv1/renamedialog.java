package com.example.almudena.mad_btv1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

/**
 * Created by Electronico2 on 28/01/2016.
 */
public class renamedialog extends DialogFragment {

    String nombre;
    EditText et;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Log.w("Aqui ha llegado", "Sep");

        builder.setMessage("Change handle")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        et = (EditText) getDialog().findViewById(R.id.nnombre);
                        nombre = et.getText().toString();


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        renamedialog.this.getDialog().cancel();
                        nombre = "%%nullname%%";

                    }
                }).setView(inflater.inflate(R.layout.renamelayout, null));
        return builder.create();
    }

    public String getNombre() {
        return nombre;
    }
}
