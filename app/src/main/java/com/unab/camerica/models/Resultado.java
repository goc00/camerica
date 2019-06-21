package com.unab.camerica.models;

public class Resultado {
    int resLocal;
    int resVisita;

    public Resultado(int resLocal, int resVisita) {
        this.resLocal = resLocal;
        this.resVisita = resVisita;
    }

    public int getResLocal() {
        return resLocal;
    }

    public void setResLocal(int resLocal) {
        this.resLocal = resLocal;
    }

    public int getResVisita() {
        return resVisita;
    }

    public void setResVisita(int resVisita) {
        this.resVisita = resVisita;
    }
}
