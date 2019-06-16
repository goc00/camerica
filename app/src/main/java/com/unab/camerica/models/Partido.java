package com.unab.camerica.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Partido implements Parcelable {
    Equipo local;
    Equipo visita;
    String hora;
    String fecha;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(local);
        dest.writeValue(visita);
        dest.writeString(hora);
        dest.writeString(fecha);
    }

    private void readFromParcel(Parcel in) {
        local = (Equipo)in.readValue(Equipo.class.getClassLoader());
        visita = (Equipo)in.readValue(Equipo.class.getClassLoader());
        hora = in.readString();
        fecha = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Partido createFromParcel(Parcel in ) {
            return new Partido(in);
        }

        public Partido[] newArray(int size) {
            return new Partido[size];
        }
    };

    // Constructor interno para implementación de Parseable
    private Partido(Parcel in) {
        readFromParcel(in);
    }

    // Constructor para "parsear" Map a objeto en sí
    public Partido(Equipo local, Equipo visita, String fecha, String hora) {
        this.local = local;
        this.visita = visita;
        this.hora = hora;
        this.fecha = fecha;
    }

    // Getters y setters


    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisita() {
        return visita;
    }

    public void setVisita(Equipo visita) {
        this.visita = visita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
