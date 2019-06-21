package com.unab.camerica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unab.camerica.adapters.PartidoAdapter;
import com.unab.camerica.models.Equipo;
import com.unab.camerica.models.Partido;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity {

    ArrayList<Equipo> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);


        // Obtiene elementos desde Firebase
        countries = getIntent().getParcelableArrayListExtra("countriesList");
        ArrayList<Partido> matches = getIntent().getParcelableArrayListExtra("matchesList");

        // Recibe el arreglo de objetos para desplegar todos los partidos
        PartidoAdapter adapter = new PartidoAdapter(this, matches);
        ListView lista = findViewById(R.id.partidos);

        View header = getLayoutInflater().inflate(R.layout.partidos_header, null);
        View footer = getLayoutInflater().inflate(R.layout.partidos_footer, null);

        lista.setAdapter(adapter);
        lista.addHeaderView(header);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPredictor();
            }
        });
    }

    /**
     * Carga predictor activity, recibiendo la lista de países
     */
    private void goToPredictor() {
        Intent intent = new Intent(this, FinalPredictorActivity.class);
        intent.putParcelableArrayListExtra("countriesList", countries);
        this.startActivity(intent);
    }

}
