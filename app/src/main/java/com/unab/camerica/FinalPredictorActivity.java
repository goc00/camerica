package com.unab.camerica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unab.camerica.constants.Cons;
import com.unab.camerica.models.Equipo;
import com.unab.camerica.models.Prediction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FinalPredictorActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText et1, et2;

    // Para mantener ids referenciados
    HashMap<Integer, Long> spinnerIds = new HashMap<Integer, Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_predictor);

        //ListView view = findViewById(R.id.predictor_layout);

        ArrayList<Equipo> countries = getIntent().getParcelableArrayListExtra("countriesList");

        // Genera String[] para la carga de los Spinners
        String[] values = new String[countries.size()];
        int i = 0;
        for(Equipo country : countries) {
            values[i] = country.getNombre();
            spinnerIds.put(i, country.getApiId()); // i hará de position
            i++;
        }

        // Referencias de elementos
        spinner1 = findViewById(R.id.country_finalist_1);
        spinner2 = findViewById(R.id.country_finalist_2);

        ArrayAdapter<String> finalistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values);

        spinner1.setAdapter(finalistAdapter);
        spinner2.setAdapter(finalistAdapter);

        // Agrega listener a botones
        addListenerOnButton();
        addListenerOnMostChosen();

        // Header
        //View header = getLayoutInflater().inflate(R.layout.final_predictor_header, null);
        //view.addHeaderView(header);
    }

    /**
     * Agrega listener al botón para su manejo de datos y escribir en Firebase
     */
    public void addListenerOnButton() {

        spinner1 = findViewById(R.id.country_finalist_1);
        spinner2 = findViewById(R.id.country_finalist_2);
        et1 = findViewById(R.id.goals_finalist_1);
        et2 = findViewById(R.id.goals_finalist_2);

        Button btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String spinner1Str = spinner1.getSelectedItem().toString();
                String spinner2Str = spinner2.getSelectedItem().toString();
                String et1Str = et1.getText().toString().trim();
                String et2Str = et2.getText().toString().trim();
                Long id1 = spinnerIds.get(spinner1.getSelectedItemPosition());
                Long id2 = spinnerIds.get(spinner2.getSelectedItemPosition());

                String msg = "";
                try {

                    // Validaciones
                    if(spinner1Str.equals(spinner2Str)) { throw new Exception(getString(R.string.identical_countries)); }
                    if(et1Str.isEmpty() || et2Str.isEmpty()) { throw new Exception(getString(R.string.fields_required)); }

                    // Ok, guarda en firebase
                    Prediction prediction = new Prediction(id1,id2,
                                                        Integer.parseInt(et1Str),
                                                        Integer.parseInt(et2Str));

                    Calendar now = Calendar.getInstance();
                    Date date = now.getTime();
                    Long idx = now.getTimeInMillis();

                    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                    db.child(Cons.FB_SAVE).child(Cons.FB_SAVE_FLAG + idx).setValue(prediction);

                    msg = getString(R.string.save_ok);

                } catch(Exception e) {
                    msg = e.getMessage();
                }

                Toast.makeText(FinalPredictorActivity.this,
                        msg,
                        Toast.LENGTH_SHORT).show();

            }

        });
    }


    /**
     * Calcula la predicción más escogida y promedio de esta
     */
    public void addListenerOnMostChosen() {

        Button btn = findViewById(R.id.btn_calculate_avg);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String msg = "";


                // Obtiene predicciones
                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(Cons.FB_SAVE);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        List<Prediction> predictions = new ArrayList();
                        for(DataSnapshot prediction : dataSnapshot.getChildren()) {
                            Prediction pred = new Prediction((HashMap)prediction.getValue());
                            predictions.add(pred);
                        }

                        // Verifica los partidos más seleccionados
                        if(predictions.size() > 0) {

                            // Interpretar elementos
                            if(predictions.size() == 1) {
                                // Pasa valores a elementos

                            } else {
                                // Se deben analizar los N elementos (siempre mayor a 1)
                            }

                        } else {
                            // Aún no hay elementos de predicción
                            Toast.makeText(FinalPredictorActivity.this,
                                    getString(R.string.no_predictions),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });

            }

        });

    }


}
