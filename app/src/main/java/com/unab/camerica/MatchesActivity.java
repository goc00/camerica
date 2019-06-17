package com.unab.camerica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
        lista.addFooterView(footer);

        // Listener para acción del botón
        final Button go2Predictor = (Button)findViewById(R.id.go_2_predictor);
        go2Predictor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Generará el intent pasándole la lista de países
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
        this.finish();
    }

}
