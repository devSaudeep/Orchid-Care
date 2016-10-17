package com.example.saulo.orchidcare;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyGroveActivity extends AppCompatActivity {

    List<String> item = null;
    private ListView listViewMyGrove;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grove);

        myDB = new DatabaseHelper(this);

        listViewMyGrove = (ListView) findViewById(R.id.listViewMyGrove);

        Cursor c = myDB.getDataForMyGroveActivity();

        item = new ArrayList<String>();

        String orchidName = "", orchidType = "", orchidDate = "";

        if (c.moveToFirst()) {
            // Recorer el cursor hasta que no haya mas registros
            do {
                orchidName = c.getString(0);
                orchidType = c.getString(1);
                orchidDate = c.getString(2);
                item.add(orchidName + " | " + orchidType + " | " + orchidDate);
            } while (c.moveToNext());
        }

        // Adapter de tipo ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, item);
        listViewMyGrove.setAdapter(adapter);

        listViewMyGrove.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyGroveActivity.this, MyGroveInfoActivity.class);
                intent.putExtra("keyName", getOrchidName(position));
                startActivity(intent);
            }
        });
    }

    private String getOrchidName(int n) {
        StringBuilder str = new StringBuilder();
        Cursor c = myDB.getDataForMyGroveActivity();
        c.moveToPosition(n);
        str.append(n + "@");
        str.append(c.getString(0));
        return str.toString();
    }
}
