package com.example.weatherapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {

    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView descriptionTextView;
    private TextView minTemperatureTextView;
    private TextView maxTemperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cityTextView = findViewById(R.id.cityNameTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        minTemperatureTextView = findViewById(R.id.minTemperatureTextView);
        maxTemperatureTextView = findViewById(R.id.maxTemperatureTextView);

        // Obtén el nombre de la ciudad de la intención
        String cityName = getIntent().getStringExtra("cityName");

        // Establecer el nombre de la ciudad en el TextView correspondiente
        cityTextView.setText(cityName);

        // Asegúrate de reemplazar los otros TextView con datos reales, como se muestra en el ejemplo anterior.
        temperatureTextView.setText("Temperatura: " + "18°C");
        descriptionTextView.setText("Descripción: " + "Soleado");
        minTemperatureTextView.setText("Temp. Mínima: " + "15°C");
        maxTemperatureTextView.setText("Temp. Máxima: " + "22°C");
    }
}
