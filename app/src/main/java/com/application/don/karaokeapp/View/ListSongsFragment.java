package com.application.don.karaokeapp.View;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.application.don.karaokeapp.Adapter.SongAdapter;
import com.application.don.karaokeapp.DTO.SongDTO;
import com.application.don.karaokeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don on 5/13/16.
 */
public class ListSongsFragment extends Fragment
{
    List<SongDTO> lstSongs;
    ListView lsvSongs;
    SongAdapter songAdapter;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_songs, container, false);

        lstSongs = new ArrayList<>();
        lstSongs.add(new SongDTO("11111", "Chắc ai đó sẽ về", "Anh tìm nỗi nhớ, anh tìm quá khứ...", false));
        lstSongs.add(new SongDTO("22222", "Let her go", "Well you only need the light, when...", true));
        lsvSongs = (ListView) view.findViewById(R.id.lsvSongs);
        songAdapter = new SongAdapter(getActivity(), lstSongs);
        lsvSongs.setAdapter(songAdapter);
        return view;
    }
}
