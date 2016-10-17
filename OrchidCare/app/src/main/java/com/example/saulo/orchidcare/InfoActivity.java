package com.example.saulo.orchidcare;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    private TextView orchidTypeTextView, orchidInfoTextView;
    private ImageView orchidImageView;
    private ArrayList<String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        myDB = new DatabaseHelper(this);
        orchidTypeTextView = (TextView) findViewById(R.id.orchidTypeTextView);
        orchidImageView = (ImageView) findViewById(R.id.orchidImageView);
        orchidInfoTextView = (TextView) findViewById(R.id.orchidInfoTextView);

        String data = getIntent().getExtras().getString("keyName");
        int position = Integer.parseInt(data);

        Cursor c = myDB.getDataForSearchActivity();

        String orchidTypeName = "";

        c.moveToPosition(position);

        orchidTypeName = c.getString(0);

        orchidTypeTextView.setText(orchidTypeName);

        orchidInfoTextView.setText(c.getString(1));
    }
}