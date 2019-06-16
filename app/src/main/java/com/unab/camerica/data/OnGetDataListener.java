package com.unab.camerica.data;

import com.google.firebase.database.DataSnapshot;

/**
 * Interfaz para controlar asincronicidad del evento onDataChange
 * para Firebase, con esto siempre se resuelve una acción en el success
 */
public interface OnGetDataListener {
    void onSuccess(DataSnapshot dataSnapshot);
    void onStart();
    void onFailure();
}
