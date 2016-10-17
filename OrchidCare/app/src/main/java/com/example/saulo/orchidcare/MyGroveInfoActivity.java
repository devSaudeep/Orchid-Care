package com.example.saulo.orchidcare;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyGroveInfoActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    private Button createButton;
    private TextView nameTextView;
    private TextView typeTextView;
    private TextView calendarTextView, newDateTextView;
    int newMonth, newDayOfMonth, newYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grove_info);

        myDB = new DatabaseHelper(this);
        nameTextView = (TextView) findViewById(R.id.nameOfOrchidTextView);
        typeTextView = (TextView) findViewById(R.id.typeTextView);
        calendarTextView = (TextView) findViewById(R.id.calendarTextView);
        newDateTextView = (TextView) findViewById(R.id.nextDateFertTextView);
        createButton = (Button) findViewById(R.id.createButtonForNextDate);

        String data = getIntent().getExtras().getString("keyName");
        String[] divided = data.split("@");
        String idx = divided[0];
        String name = divided[1];

        int idxInt = Integer.parseInt(idx);

        nameTextView.setText(name);

        final Cursor c = myDB.getDataForMyGroveActivity();

        c.moveToPosition(idxInt);

        final String type = c.getString(1);
        final String calendar = c.getString(2);

        typeTextView.setText(type);


        calendarTextView.setText(calendar);

        createButton.setOnClickListener(new View.OnClickListener() {
            String[] dateDivided = calendar.split("/");
            int month = Integer.parseInt(dateDivided[0]);
            int dayOfMonth = Integer.parseInt(dateDivided[1]);
            int year = Integer.parseInt(dateDivided[2]);
            @Override
            public void onClick(View v) {
                //Create new date
                newDayOfMonth = dayOfMonth + 7;
                if (newDayOfMonth > 29) {
                    int diff = newDayOfMonth - 29;
                    newDayOfMonth = diff;
                    newMonth = month + 1;
                    if (newMonth > 12) {
                        int d = newMonth - 12;
                        newMonth = d;
                        newYear = year + 1;
                    }
                } else newMonth = month;
                newYear = year;

                StringBuilder newDateStr = new StringBuilder();
                newDateStr.append(newMonth + "/" + newDayOfMonth + "/" + newYear);
                String newDate = newDateStr.toString();
                newDateTextView.setText(newDate);
            }
        });
    }
}