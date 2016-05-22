package com.application.don.karaokesearch.Activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.application.don.karaokesearch.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListSongsFragment listSongsFragment;
    FragmentTransaction fragmentTransaction;
    String fragmentVisited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_arirang_songs);
        fragmentVisited = "Arirang";
        ShowFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("karaokeType", fragmentVisited);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_favorite) {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            intent.putExtra("karaokeType", fragmentVisited);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_arirang_songs) {
            item.setChecked(true);
            fragmentVisited = "Arirang";
            ShowFragment();
            // Handle the camera action
        } else if (id == R.id.nav_music_core_songs) {
            item.setChecked(true);
            fragmentVisited = "Music Core";
            ShowFragment();

        } else if (id == R.id.nav_california_songs) {
            item.setChecked(true);
            fragmentVisited = "California";
            ShowFragment();

        } else if (id == R.id.nav_update) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_instruction) {

        } else if (id == R.id.nav_info) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ShowFragment() {
        setTitle(fragmentVisited);
        listSongsFragment = new ListSongsFragment();
        listSongsFragment = ListSongsFragment.newInstance(fragmentVisited);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, listSongsFragment);
        fragmentTransaction.commit();
    }
}
