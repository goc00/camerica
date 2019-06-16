package com.unab.camerica.models;

public class Partido {
    Equipo local;
    Equipo visita;
    String hora;
    String fecha;

    public Partido(Equipo local, Equipo visita, String hora, String fecha) {
        this.local = local;
        this.visita = visita;
        this.hora = hora;
        this.fecha = fecha;
    }

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
