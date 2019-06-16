package com.unab.camerica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.unab.camerica.adapters.PartidoAdapter;
import com.unab.camerica.models.Equipo;
import com.unab.camerica.models.Partido;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        //ArrayList<Equipo> countries = getIntent().getParcelableArrayListExtra("countriesList");
        ArrayList<Partido> matches = getIntent().getParcelableArrayListExtra("matchesList");

        PartidoAdapter adapter = new PartidoAdapter(this, matches);
        ListView lista = findViewById(R.id.partidos);

        //View header = getLayoutInflater().inflate(R.layout.partidos_header, null);

        lista.setAdapter(adapter);
        //lista.addHeaderView(header);
    }

}
