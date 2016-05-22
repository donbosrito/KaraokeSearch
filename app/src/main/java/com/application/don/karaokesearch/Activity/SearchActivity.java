package com.application.don.karaokesearch.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.application.don.karaokesearch.Adapter.SongAdapter;
import com.application.don.karaokesearch.Model.Song;
import com.application.don.karaokesearch.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ActionBar actionBar;
    EditText edtSearch;
    ListView lsvSearchSongs;
    RadioButton rbtSongName, rbtLyric, rbtSinger, rbtShortName;
    SongAdapter adapter;
    List<Song> lstSearchSongs;
    String karaokeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        initActionBar();

        Intent intent = getIntent();
        karaokeType = intent.getStringExtra("karaokeType");

        AddControls();
        AddEvents();
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LinearLayout actionBarLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.custom_actionbar_search, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        edtSearch = (EditText) actionBarLayout.findViewById(R.id.edtSearch);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(actionBarLayout, params);
    }

    private void AddControls() {
        lsvSearchSongs = (ListView) findViewById(R.id.lsvSearchSongs);
        rbtSongName = (RadioButton) findViewById(R.id.rbtSongName);
        rbtLyric = (RadioButton) findViewById(R.id.rbtLyric);
        rbtSinger = (RadioButton) findViewById(R.id.rbtSinger);
        rbtShortName = (RadioButton) findViewById(R.id.rbtShortName);
    }

    private void AddEvents() {
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    edtSearch.clearFocus();
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        lsvSearchSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, SongDetailActivity.class);
                intent.putExtra("code", lstSearchSongs.get(position).get_code());
                startActivity(intent);
            }
        });
    }

    //Tìm kiếm Song ở đây với loại karaoke lấy từ biến karaokeType và từ khóa là edtSearch.getText()
    // sau đó gán dữ liệu vào List lstSearchSongs.
    private void performSearch() {
        lstSearchSongs = new ArrayList<>();
        if (rbtSongName.isChecked()) {
            //Tìm theo tên
            lstSearchSongs.add(new Song("55205", "123 Chia tay", "Một là yêu em, hai là bên em, ba là anh chỉ có em...", true));
        }
        else if (rbtLyric.isChecked()){
            lstSearchSongs.add(new Song("830535", "1 ngôi sao", "Một ngày nhớ nhớ bóng hình em trái tim mong chờ những phút...", true));
            //Tìm theo lời bài hát
        }
        else if (rbtSinger.isChecked()) {
            lstSearchSongs.add(new Song("60002", "123 Chia tay", "Một là yêu em, hai là bên em, ba là anh chỉ có em...", true));
            //Tìm theo ca sĩ
        }
        else if (rbtShortName.isChecked()) {
            //Tìm nhanh tên bài hát
        }
        adapter = new SongAdapter(this, lstSearchSongs);
        lsvSearchSongs.setAdapter(adapter);
        lsvSearchSongs.setEmptyView(findViewById(R.id.emptyElement));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
