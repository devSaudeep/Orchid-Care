package com.example.saulo.orchidcare;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    List<String> item = null;
    private EditText editText;
    private ArrayAdapter<String> listViewAdapter;
    private ListView listView;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        myDB = new DatabaseHelper(this);

//        myDB.insertDataToSearchTable(getResources().getString(R.string.catasetum), getResources().getString(R.string.catasetum_desc));
//        myDB.insertDataToSearchTable(getResources().getString(R.string.vanda), getResources().getString(R.string.vanda_desc));
//        myDB.insertDataToSearchTable(getResources().getString(R.string.phalaenopsis), getResources().getString(R.string.phalaenopsis_desc));
//        myDB.insertDataToSearchTable(getResources().getString(R.string.dendrobium), getResources().getString(R.string.dendrobium_desc));
//        myDB.insertDataToSearchTable(getResources().getString(R.string.miltonia), getResources().getString(R.string.miltonia_desc));
//        myDB.insertDataToSearchTable(getResources().getString(R.string.novice_dendrobium), getResources().getString(R.string.novice_dendrobium_desc));
//        myDB.insertDataToSearchTable(getResources().getString(R.string.odontoglossum), getResources().getString(R.string.odontoglossum_desc));

        listView = (ListView) findViewById(R.id.listView2);

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
        // Adapter de tipo ArrayAdapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, InfoActivity.class);
                intent.putExtra("keyName", String.valueOf(position));
                startActivity(intent);
            }
        });
    }
}