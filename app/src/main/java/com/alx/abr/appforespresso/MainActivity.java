package com.alx.abr.appforespresso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = ((EditText) findViewById(R.id.editText_name)).getText().toString();
                if(str.length() > 0) {
                    findViewById(R.id.textView_error).setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(MainActivity.this, BasicActivity.class);
                    intent.putExtra("name", str);
                    startActivity(intent);
                } else {
                    findViewById(R.id.textView_error).setVisibility(View.VISIBLE);
                }
            }
        });
    }


}