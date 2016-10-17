package com.example.saulo.orchidcare;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddOrchidActivity extends AppCompatActivity {

    private ArrayAdapter<String> spinnerAdapter;
    private EditText nameOfOrchidText;
    private String[] spinnerData;
    private Button createReminderButton;
    private CalendarView calendar;
    private String calendarDateStr;
    private Spinner orchidSpinner;
    List<String> item = null;


    DatabaseHelper myDB;

    public static String orchidName, typeOrchid, dateOrchid;

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_orchid);

        myDB = new DatabaseHelper(this);


        calendar = (CalendarView) findViewById(R.id.calendarView);
        nameOfOrchidText = (EditText) findViewById(R.id.nameOfOrchidEditText);
        createReminderButton = (Button) findViewById(R.id.reminderCreationButton);
        orchidSpinner = (Spinner) findViewById(R.id.orchidSpinner);

        item = populateSpinnerWithDB();

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        orchidSpinner.setAdapter(spinnerAdapter);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendarDateStr = month + "/" + dayOfMonth + "/" + year;
            }
        });

        createReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orchidName = nameOfOrchidText.getText().toString();
                typeOrchid = orchidSpinner.getSelectedItem().toString();
                dateOrchid = calendarDateStr;

                boolean isInserted = myDB.insertDataToAddOrchidTable(orchidName, typeOrchid, dateOrchid);

                if(isInserted = true)
                    Toast.makeText(AddOrchidActivity.this, "Orchid Added Succesfully!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(AddOrchidActivity.this, "Error, Orchid was not added to the database!", Toast.LENGTH_LONG).show();

                nameOfOrchidText.setText("");
                orchidSpinner.setSelection(0);
                orchidSpinner.setAdapter(spinnerAdapter);
                finish();
            }
        });
    }

    private List<String> populateSpinnerWithDB() {
        Cursor c = myDB.getDataForSearchActivity();
        item = new ArrayList<String>();
        String orchidTypeName = "";
        if (c.moveToFirst()) {
            // Recorer el cursor hasta que no haya mas registros
            do {
                orchidTypeName = c.getString(0);
                item.add(orchidTypeName);
            } while (c.moveToNext());
        }
        return item;
    }
}