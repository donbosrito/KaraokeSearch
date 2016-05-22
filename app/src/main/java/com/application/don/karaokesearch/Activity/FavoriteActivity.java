package com.application.don.karaokesearch.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.application.don.karaokesearch.Adapter.SongAdapter;
import com.application.don.karaokesearch.Model.Song;
import com.application.don.karaokesearch.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    List<Song> lstFavSongs;
    SongAdapter songAdapter;
    ListView lsvFavSongs;
    String karaokeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        lsvFavSongs = (ListView) findViewById(R.id.lsvFavSongs);

        Intent intent = getIntent();
        karaokeType = intent.getStringExtra("karaokeType");

        //Show button back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AddEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showListFavSong();
    }

    private void AddEvents() {
        lsvFavSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoriteActivity.this, SongDetailActivity.class);
                intent.putExtra("code", lstFavSongs.get(position).get_code());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showListFavSong() {
        //Đưa dữ liệu danh sách yêu thích theo loại karaoke.
        switch (karaokeType) {
            case "Arirang":
                lstFavSongs = new ArrayList<>();
                lstFavSongs.add(new Song("55205", "123 Chia tay", "Một là yêu em, hai là bên em, ba là anh chỉ có em...", true));
                break;
            case "Music Core":
                lstFavSongs = new ArrayList<>();
                lstFavSongs.add(new Song("830535", "1 ngôi sao", "Một ngày nhớ nhớ bóng hình em trái tim mong chờ những phút...", true));
                break;
            case "California":
                lstFavSongs = new ArrayList<>();
                lstFavSongs.add(new Song("60002", "123 Chia tay", "Một là yêu em, hai là bên em, ba là anh chỉ có em...", true));
                break;
        }
        songAdapter = new SongAdapter(getApplicationContext(), lstFavSongs);
        lsvFavSongs.setAdapter(songAdapter);
        lsvFavSongs.setEmptyView(findViewById(R.id.emptyElement));
    }
}
