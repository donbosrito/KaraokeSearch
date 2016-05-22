package com.application.don.karaokesearch.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.application.don.karaokesearch.Model.Song;
import com.application.don.karaokesearch.R;

import org.w3c.dom.Text;

public class SongDetailActivity extends AppCompatActivity {

    TextView txtCode, txtSongName, txtSinger, txtKaraokePlayer, txtVolume, txtLyric;
    CheckBox cbxFav;
    Button btnTryListening;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        AddControls();
        AddEvents();

        //Show back arrow button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        //Lấy bài hát từ mã số code.
        song = new Song(code, "Chắc ai đó sẽ về", "Anh tìm nỗi nhớ, anh tìm quá khứ...", true);

        AddDataToControl();

    }

    private void AddDataToControl() {
        txtCode.setText(song.get_code());
        txtSongName.setText(song.get_name());
        txtLyric.setText(song.get_lyric());
        txtKaraokePlayer.setText("Arirang");
        txtVolume.setText("57");
        txtSinger.setText("Sơn Tùng MTP");
        cbxFav.setChecked(song.is_favorite());
    }

    private void AddControls() {
        txtCode = (TextView) findViewById(R.id.txtCode);
        txtSongName = (TextView) findViewById(R.id.txtSongName);
        txtSinger = (TextView) findViewById(R.id.txtSinger);
        txtKaraokePlayer = (TextView) findViewById(R.id.txtKaraokePlayer);
        txtVolume = (TextView) findViewById(R.id.txtVolume);
        txtLyric = (TextView) findViewById(R.id.txtLyric);
        cbxFav = (CheckBox) findViewById(R.id.cbxFav);
        btnTryListening = (Button) findViewById(R.id.btnTryListening);
    }

    private void AddEvents() {
        cbxFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Sự kiện bấm nút yêu thích...
                //Set dữ liệu với biến bool cbxFav.isChecked();
            }
        });

        btnTryListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sự kiện nghe thử bài hát...
                //Cứ làm mấy phần kia trước đi, phần này để làm sau
                //Để tìm hiểu kĩ về nghe trực tuyến trên mp3.zing.vn đã.
            }
        });
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
