package com.unab.camerica.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Equipo implements Parcelable {

    Long apiId;
    String bandera;
    String codigo;
    String nombre;

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(apiId);
        dest.writeString(bandera);
        dest.writeString(codigo);
        dest.writeString(nombre);
    }

    private void readFromParcel(Parcel in) {
        apiId = in.readLong();
        bandera = in.readString();
        codigo = in.readString();
        nombre = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Equipo createFromParcel(Parcel in ) {
            return new Equipo(in);
        }

        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };

    // Constructor interno para implementación de Parseable
    private Equipo(Parcel in) {
        readFromParcel(in);
    }

    // Constructor para "parsear" Map a objeto en sí
    public Equipo(Map map) {
        this.apiId = (Long)map.get("api_id");
        this.bandera = map.get("bandera").toString();
        this.codigo = map.get("codigo").toString();
        this.nombre = map.get("nombre").toString();
    }

    // Getters y setters
    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
