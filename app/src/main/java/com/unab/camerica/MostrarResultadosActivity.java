package com.unab.camerica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unab.camerica.constants.Cons;
import com.unab.camerica.models.Equipo;
import com.unab.camerica.models.Partido;
import com.unab.camerica.models.Prediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MostrarResultadosActivity extends AppCompatActivity {

    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    ArrayList<HashMap<String, Object>> listaResultados = new ArrayList<>();
    ArrayList<String> listaDatos = new ArrayList<String>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_resultados);

       // loadResult();
        //setDataAndStart();
        addListenerOnMostChosen();

    }

    /*private void loadResult() {

        DatabaseReference countriesDB = db.child(Cons.FB_COUNTRIES);
        countriesDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot country : dataSnapshot.getChildren()) {
                    listaResultados.add((HashMap)country.getValue());
                    listaDatos.add((String)country.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        //mostrar información
    }*/

    public void addListenerOnMostChosen() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(Cons.FB_SAVE);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<String> selectedCountries = new ArrayList<String>();

                    for(DataSnapshot prediction : dataSnapshot.getChildren()) {
                        Prediction pred = new Prediction((HashMap)prediction.getValue());

                        try {
                            String versus = "";
                            if (pred.getCountryName1() != null && !pred.getCountryName1().isEmpty()) {
                                selectedCountries.add(pred.getCountryName1());
                                versus = pred.getCountryName1();

                            }

                            if (pred.getCountryName2() != null && !pred.getCountryName2().isEmpty()) {
                                selectedCountries.add(pred.getCountryName2());
                                versus += " " +pred.getCountryName2();
                            }
                            listaDatos.add(versus);
                        } catch (Exception e) {
                            // TODO
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });
        setDataAndStart();
    }


    private void setDataAndStart() {

        ListView simpleList;
        listaDatos.add("chile vs argentina 4-1");
        listaDatos.add("peru vs colombia 6-1 ");
        simpleList = (ListView) findViewById(R.id.listaResultadosVotacion);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_mostrar_resultados_item, R.id.textViewResultados, listaDatos);
        simpleList.setAdapter(arrayAdapter);
    }


}
