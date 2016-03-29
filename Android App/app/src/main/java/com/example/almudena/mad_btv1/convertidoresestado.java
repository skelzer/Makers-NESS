package com.example.almudena.mad_btv1;

/**
 * Created by Electronico2 on 26/01/2016.
 */
public class convertidoresestado {
    public int est_stint(String estado) {
        switch (estado) {

            case "Frío": {
                return 0;
            }
            case "Caliente": {
                return 1;
            }
            case "Recirculando": {
                return 2;
            }
            case "Error": {
                return 3;
            }
            default: {
                return 3;
            }
        }

    }

    public String est_intst(int estado) {
        switch (estado) {

            case 0: {
                return "Frío";
            }
            case 1: {
                return "Caliente";
            }
            case 2: {
                return "Recirculando";
            }
            case 3: {
                return "Error";
            }
            default: {
                return "Error";
            }
        }

    }
}
