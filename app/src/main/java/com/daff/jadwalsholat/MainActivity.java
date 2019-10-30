package com.daff.jadwalsholat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText searchCity;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCity = findViewById(R.id.search_city);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = searchCity.getText().toString().trim();

                Intent intent = new Intent(MainActivity.this, JadwalSholat.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });
    }

}

