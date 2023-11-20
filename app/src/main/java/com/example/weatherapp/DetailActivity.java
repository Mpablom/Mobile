package com.example.weatherapp;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.FontResourcesParserCompat;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView cityNameTextView;
    private TextView currentTemperatureTextView;
    private TextView weatherDescriptionTextView;
    private TextView minTemperatureTextView;
    private TextView maxTemperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cityNameTextView = findViewById(R.id.cityNameTextView);
        currentTemperatureTextView = findViewById(R.id.temperatureTextView);
        weatherDescriptionTextView = findViewById(R.id.descriptionTextView);
        minTemperatureTextView = findViewById(R.id.minTemperatureTextView);
        maxTemperatureTextView = findViewById(R.id.maxTemperatureTextView);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");

        cityNameTextView.setText("Nombre de la ciudad: " + cityName);

        new FetchWeatherDataTask().execute(cityName);
    }

    private class FetchWeatherDataTask extends AsyncTask<String, Void, WeatherResponse> {
        @Override
        protected WeatherResponse doInBackground(String... params) {
            String cityName = params[0];
            String apiKey = "43218d3f78f27f96b36332ea59aba692";
            WeatherApi weatherApi = RetrofitClient.getRetrofitInstance().create(WeatherApi.class);
            Call<WeatherResponse> call = weatherApi.getCurrentWeather(cityName, "metric", "es", apiKey);

            try {
                Response<WeatherResponse> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(WeatherResponse weatherData) {
            if (weatherData != null) {
                double currentTemp = weatherData.getMain().getTemp();
                String description = weatherData.getWeather().get(0).getDescription();
                double minTemp = weatherData.getMain().getTemp_min();
                double maxTemp = weatherData.getMain().getTemp_max();

                currentTemperatureTextView.setText("Temperatura Actual: " + currentTemp + "°C");
                weatherDescriptionTextView.setText("Descripción del clima: " + description);
                minTemperatureTextView.setText("Temperatura Mínima: " + minTemp + "°C");
                maxTemperatureTextView.setText("Temperatura Máxima: " + maxTemp + "°C");
            }
        }
    }
}
