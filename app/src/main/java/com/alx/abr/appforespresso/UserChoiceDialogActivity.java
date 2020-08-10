package com.alx.abr.appforespresso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserChoiceDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice_dialog);

        String userChoice = getIntent().getStringExtra("userChoice");
        ((TextView) findViewById(R.id.textView_userChoice)).setText(userChoice);
    }
}