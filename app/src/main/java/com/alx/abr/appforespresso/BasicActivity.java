package com.alx.abr.appforespresso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BasicActivity extends AppCompatActivity {
    private TextView textView_action_mode;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        String user_name = getIntent().getStringExtra("name");
        ((TextView) findViewById(R.id.textView_user_name))
                .setText(String.format("Привет, %s!", user_name));

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.cities)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    ((TextView) findViewById(R.id.textView_city)).setText(
                            String.format("Ваш город %s!", parent.getSelectedItem()));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerCarsAdapter(this));

        findViewById(R.id.button_datepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        findViewById(R.id.button_timepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        findViewById(R.id.button_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        textView_action_mode = findViewById(R.id.textView_action_mode);
        findViewById(R.id.button_toggle_actionmode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActionMode();
            }
        });
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                toShowUserTimeDateActivity("Ваше время: " + hourOfDay + ":" + minute);
            }
        }, hour, minute, true);
        timePicker.setTitle("Выберите время");
        timePicker.show();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                toShowUserTimeDateActivity("Ваша дата: " + dayOfMonth + "." + monthOfYear + "." + year);
            }
        }, 2020, 0, 1);
        datePicker.setTitle("Выберите дату");
        datePicker.show();
    }

    private void toShowUserTimeDateActivity(String str) {
        Intent intent = new Intent(BasicActivity.this, UserTimeDateActivity.class);
        intent.putExtra("userTimeDate", str);
        startActivity(intent);
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);

        builder.setNegativeButton("Зеленый", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toShowUserChoiceDialogActivity("Ты выбрал зеленый цвет!");
            }
        });

        builder.setPositiveButton("Черный", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toShowUserChoiceDialogActivity("Ты выбрал черный цвет!");
            }
        });
        builder.create().show();
    }

    private void toShowUserChoiceDialogActivity(String str) {
        Intent intent = new Intent(BasicActivity.this, UserChoiceDialogActivity.class);
        intent.putExtra("userChoice", str);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                updateActionModeText(R.string.action_settings);
                break;
            case R.id.action_about:
                updateActionModeText(R.string.action_about);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startActionMode() {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallbacks);
        }
    }

    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_one:
                    updateActionModeText(R.string.action_mode_one);
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            BasicActivity.this.actionMode = null;
        }
    };

    private void updateActionModeText(int actionModeId) {
        textView_action_mode.setText(actionModeId);
    }
}