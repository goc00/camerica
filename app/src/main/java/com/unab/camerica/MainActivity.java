package com.unab.camerica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.unab.camerica.adapters.PartidoAdapter;
import com.unab.camerica.data.CountriesData;
import com.unab.camerica.models.Equipo;
import com.unab.camerica.models.Partido;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtiene todos los equipos (países)
        CountriesData countriesData = new CountriesData();
        List<Equipo> equipos = countriesData.getAllCountries();

        // String apiId, String bandera, String codigo, String nombre
        Equipo equipo1 = new Equipo("equipo_1", "BR", "BRA", "Brasil");
        Equipo equipo2 = new Equipo("equipo_2", "BO", "BOL", "Bolivia");

        Partido partido = new Partido(equipo1, equipo2, "12:00", "10-06");

        Partido[] partidos = new Partido[1];
        partidos[0] = partido;

        PartidoAdapter adapter = new PartidoAdapter(this, partidos);
        ListView lista = findViewById(R.id.partidos);

        lista.setAdapter(adapter);

    }
}
