package com.daff.jadwalsholat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.daff.jadwalsholat.model.ItemsItem;
import com.daff.jadwalsholat.model.ResponseSchedule;
import com.daff.jadwalsholat.networking.APIService;
import com.daff.jadwalsholat.networking.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalSholat extends AppCompatActivity {

    TextView shubuh, dzhuhur, ashar, maghrib, isya, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat);

        shubuh = findViewById(R.id.shubuh);
        dzhuhur = findViewById(R.id.dzhuhur);
        ashar= findViewById(R.id.ashar);
        maghrib = findViewById(R.id.maghrib);
        isya = findViewById(R.id.isya);
        location = findViewById(R.id.location);

        loadData();

    }

    void loadData(){

        Intent ambilData = getIntent();
        String city = ambilData.getStringExtra("city");

        APIService service = ConfigRetrofit.getAPI().create(APIService.class);
        service.getJadwal(city).enqueue(new Callback<ResponseSchedule>() {
            @Override
            public void onResponse(Call<ResponseSchedule> call, Response<ResponseSchedule> response) {
                List<ItemsItem> jadwal = response.body().getItems();

                location.setText(String.format(response.body().getQuery() + "%s"
                        , ", " + response.body().getState() + ", " + response.body().getCountry()));

                for (ItemsItem data: jadwal){
                    shubuh.setText(data.getFajr());
                    dzhuhur.setText(data.getDhuhr());
                    ashar.setText(data.getAsr());
                    maghrib.setText(data.getMaghrib());
                    isya.setText(data.getIsha());
                }

                if (response.isSuccessful()){
                    Log.d("RESPONSE", "SUCCESS");
                }else {
                    Log.d("RESPONSE", "FAILED TO FETCH DATA");
                }
            }

            @Override
            public void onFailure(Call<ResponseSchedule> call, Throwable t) {
                Log.e("RESPONSE", "ERROR");
                t.printStackTrace();
            }
        });
    }

}


