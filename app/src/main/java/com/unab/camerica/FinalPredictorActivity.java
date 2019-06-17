package com.unab.camerica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.unab.camerica.models.Equipo;

import java.util.ArrayList;

public class FinalPredictorActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_predictor);

        ArrayList<Equipo> countries = getIntent().getParcelableArrayListExtra("countriesList");

        // Genera String[] para la carga de los Spinners
        String[] values = new String[countries.size()];
        int i = 0;
        for(Equipo country : countries) {
            values[i++] = country.getNombre();
        }

        // Referencias de elementos
        spinner1 = findViewById(R.id.country_finalist_1);
        spinner2 = findViewById(R.id.country_finalist_2);

        ArrayAdapter<String> finalistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values);

        spinner1.setAdapter(finalistAdapter);
        spinner2.setAdapter(finalistAdapter);

        // Agrega listener a botón
        addListenerOnButton();
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

                try {

                    // Validaciones
                    if(spinner1Str.equals(spinner2Str)) { throw new Exception(getString(R.string.identical_countries)); }
                    if(et1Str.isEmpty() || et2Str.isEmpty()) { throw new Exception(getString(R.string.fields_required)); }

                    // Ok, guarda en firebase


                } catch(Exception e) {
                    Toast.makeText(FinalPredictorActivity.this,
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }

        });
    }


}
