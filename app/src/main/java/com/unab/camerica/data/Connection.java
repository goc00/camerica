package com.unab.camerica.data;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para conexión a Firebase
 * Utilizará addListenerForSingleValueEvent porque solo requerimos la obtención
 * de los datos y alguna que otra operación de inserción, no es necesario estar en tiempo real
 */
public class Connection {

    protected List<Object> childs = new ArrayList<>();

    public Connection() { }

    /**
     * Obtiene todos los elementos relacionados a un nodo
     * @param node Nombre del nodo
     * @return Nodos hijo relacionados al nodo recibido
     */
    protected List<Object> getElements(String node) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(node);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Obtiene de inmediato una lista de objetos para que sean casteados
                // al tipo de objeto que sea donde se requiera
                Map<String, Object> td = (HashMap<String,Object>)dataSnapshot.getValue();
                childs = new ArrayList<>(td.values());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        return childs;
    }

}
