package com.alx.abr.appforespresso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CarDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        String brand = getIntent().getStringExtra("brand");
        String model = getIntent().getStringExtra("model");

        ((TextView) findViewById(R.id.textView_car)).setText(
                String.format("Вы выбрали %s %s, хороший выбор!", brand, model));
    }
}