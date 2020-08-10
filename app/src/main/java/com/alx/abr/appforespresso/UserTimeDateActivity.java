package com.alx.abr.appforespresso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserTimeDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_time_date);

        String user_data_time = getIntent().getStringExtra("userTimeDate");
        ((TextView) findViewById(R.id.textView_user_date_time)).setText(user_data_time);
    }
}