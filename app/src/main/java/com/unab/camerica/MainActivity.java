package com.unab.camerica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unab.camerica.constants.Cons;
import com.unab.camerica.models.Equipo;
import com.unab.camerica.models.Partido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    List<Partido> matches = new ArrayList<>();
    List<Equipo> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Genera la carga de datos para disponibilizarlos a las siguientes actividades
        loadCountries();
    }

    /**
     * Carga datos respectivos a los países y luego continúa con los partidos
     */
    private void loadCountries() {

        DatabaseReference countriesDB = db.child(Cons.FB_COUNTRIES);
        countriesDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Obtiene elementos respectivos y carga los siguientes

                for(DataSnapshot country : dataSnapshot.getChildren()) {
                    // Objeto recibe el mapa para construir el elemento
                    Equipo equipo = new Equipo((HashMap)country.getValue());
                    countries.add(equipo);
                }

                // Continúa con la carga de partidos
                loadMatches();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    /**
     * Carga de partidos y luego envío de arreglos a actividad de despliegue de datos
     */
    private void loadMatches() {
        DatabaseReference matchesDB = db.child(Cons.FB_MATCHES);
        matchesDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //
                for(DataSnapshot partido : dataSnapshot.getChildren()) {
                    // Objeto recibe el mapa para construir el elemento
                    Map map = (HashMap)partido.getValue();

                    Equipo localTeam = countries.get(Integer.parseInt(map.get("local").toString()));
                    Equipo visitaTeam = countries.get(Integer.parseInt(map.get("visita").toString()));

                    Partido match = new Partido(localTeam,
                                                visitaTeam,
                                                map.get("fecha").toString(),
                                                map.get("hora").toString());
                    matches.add(match);
                }

                // Setea toda la data para que esté disponible
                setDataAndStart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    /**
     * Disponibiliza data obtenida hacia las siguientes actividades
     */
    private void setDataAndStart() {

        Intent intent = new Intent(this, MatchesActivity.class);

        // Pasa elementos obtenidos desde Firebase
        intent.putParcelableArrayListExtra("countriesList", (ArrayList<? extends Parcelable>)countries);
        intent.putParcelableArrayListExtra("matchesList", (ArrayList<? extends Parcelable>)matches);

        this.startActivity(intent);
        this.finish();

    }

}
