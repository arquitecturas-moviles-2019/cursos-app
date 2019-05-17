package com.arquitecturasmoviles.asado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.arquitecturasmoviles.asado.ui.main.SectionsPagerAdapter;


import java.util.ArrayList;

public class MyCoursesAndEventsActivity extends AppCompatActivity {

    private static final String USER_TOKEN_KEY = "UserToken";
    private static final String USER_ID_KEY = "UserId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses_and_events);

        Toolbar myToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter.addFragments(new MyCoursesFragment(), "Mis Cursos");
        sectionsPagerAdapter.addFragments(new EventsFragment(), "Eventos");
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Toast.makeText(MyCoursesAndEventsActivity.this, "Successful Logout: ",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MyCoursesAndEventsActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("logout", true);
            startActivity(intent);
        }
        return true;
    }

}