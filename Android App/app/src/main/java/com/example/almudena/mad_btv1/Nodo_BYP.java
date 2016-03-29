package com.example.almudena.mad_btv1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Almudena on 24/11/2015.
 */
public class Nodo_BYP implements Parcelable {
    public static final Parcelable.Creator<Nodo_BYP> CREATOR = new Parcelable.Creator<Nodo_BYP>() {
        public Nodo_BYP createFromParcel(Parcel in) {
            return new Nodo_BYP(in.readString(), in.readString(), in.readString());
        }

        public Nodo_BYP[] newArray(int size) {
            return new Nodo_BYP[size];
        }
    };
    String IDnodo, estado, IDArtik = "";
    Boolean AlarmClock=false;
    public Boolean getAlarmClock(){
        return AlarmClock;
    }
    public Nodo_BYP(String ID, String estado, String IDArtik) {
        IDnodo = ID;
        this.estado = estado;
        this.IDArtik = IDArtik;
    }
    public Nodo_BYP(String Id, String estado, String IDArtik, boolean Alarma){
        this(Id,estado,IDArtik);
        AlarmClock=Alarma;
    }
    public void setAlarmClock(){
        AlarmClock=true;
    }
    public void clearAlarmClock(){
        AlarmClock=false;
    }
    public void toggleAlarmClock(){AlarmClock=!AlarmClock;}
    public String getIDnodo() {
        return IDnodo;
    }

    public void setIDnodo(String idartik) {
        this.IDnodo = idartik;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIDArtik() {
        return IDArtik;
    }

    public void setIDArtik(String idartik) {
        this.IDArtik = idartik;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(IDnodo);
        dest.writeString(estado);
        dest.writeString(IDArtik);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
