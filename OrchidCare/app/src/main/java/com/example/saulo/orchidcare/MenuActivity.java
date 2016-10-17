package com.example.saulo.orchidcare;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private ArrayAdapter<String> menuAdapter;
    private int[] menuStringIdx = {0, 1, 2, 3, 4};
    private DrawerLayout menuDrawer;    //DrawerLayout init as menuDrawer
    private ListView menuList;  //ListView init as menuList
    private ActionBarDrawerToggle menuToggle;
    Context ctx = this;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        myDB = new DatabaseHelper(this);

        menuDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuList = (ListView) findViewById(R.id.left_drawer);

        addDrawerItems();

        menuDrawer.openDrawer(Gravity.LEFT);

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == menuStringIdx[0]) {
                    Toast.makeText(MenuActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    menuDrawer.closeDrawers();
                }
                else if(position == menuStringIdx[1]) {
                    Toast.makeText(MenuActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent searchIntent = new Intent(view.getContext(), SearchActivity.class);
                    startActivity(searchIntent);
                }
                else if(position == menuStringIdx[2]) {
                    Toast.makeText(MenuActivity.this, "Add Orchid", Toast.LENGTH_SHORT).show();
                    Intent addIntent = new Intent(view.getContext(), AddOrchidActivity.class);
                    startActivity(addIntent);
                }
                else if(position == menuStringIdx[3]) {
                    Toast.makeText(MenuActivity.this, "My Grove", Toast.LENGTH_SHORT).show();
                    Intent groveIntent = new Intent(view.getContext(), MyGroveActivity.class);
                    startActivity(groveIntent);
                }
                else {
                    Toast.makeText(MenuActivity.this, "About", Toast.LENGTH_SHORT).show();
                    Intent aboutIntent = new Intent(view.getContext(), AboutActivity.class);
                    startActivity(aboutIntent);
                }
            }
        });
    }

    private void addDrawerItems() {
        String[] menuStrings = {"Home", "Search", "Add Orchid", "My Grove", "About"};
        menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuStrings);
        menuList.setAdapter(menuAdapter);
    }
}