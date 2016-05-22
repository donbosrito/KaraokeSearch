package com.application.don.karaokesearch.Activity;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.don.karaokesearch.Adapter.SongAdapter;
import com.application.don.karaokesearch.Model.Song;
import com.application.don.karaokesearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don on 5/22/16.
 */
public class ListSongsFragment extends Fragment
{
    List<Song> lstSongs;
    ListView lsvSongs;
    SongAdapter songAdapter;
    Spinner spnSelectVol;
    String karaokeType;
    View view;

    public static ListSongsFragment newInstance(String strArg1) {
        ListSongsFragment listSongsFragment = new ListSongsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("karaoke_type", strArg1);
        listSongsFragment.setArguments(bundle);
        return listSongsFragment;
    }// newInstance

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_songs, container, false);

        //Lấy dữ liệu loại karaoke từ mainActivity
        Bundle arguments = getArguments();
        karaokeType = arguments.getString("karaoke_type", "");

        lsvSongs = (ListView) view.findViewById(R.id.lsvSongs);
        spnSelectVol = (Spinner) view.findViewById(R.id.spnSelectVol);
        addItemToSpinner();

        AddEvents();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showKaraoke();
    }

    private void AddEvents() {
        spnSelectVol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Lựa chọn spinner sẽ xuất ra danh sách các vol tại đây
                //Chỉnh sửa lại chỗ đưa dữ liệu vào.. thêm tham số vols vào showKaraoke() để biết mà lấy dữ liệu đúng theo vols.
                Toast.makeText(getActivity(),"OK", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spnSelectVol.setSelection(0);
            }
        });


        lsvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SongDetailActivity.class);
                intent.putExtra("code", lstSongs.get(position).get_code());
                startActivity(intent);
            }
        });
    }

    public void showKaraoke() {
        lstSongs = new ArrayList<>();

        //Đưa dữ liệu karaoke theo loại vào lSongs
        switch (karaokeType)
        {
            case "Arirang":
                lstSongs.add(new Song("54630", "123 Chia đôi lối về", "Nuốt nước mắt khẽ ôm em ấm lòng...", false));
                lstSongs.add(new Song("55205", "123 Chia tay", "Một là yêu em, hai là bên em, ba là anh chỉ có em...", true));
                break;
            case "Music Core":
                lstSongs.add(new Song("831431", "123 DZÔ", "Uống cho say cùng vui em ơi! Uống cho say cùng vui anh ơi! Anh em ta...", false));
                lstSongs.add(new Song("830535", "1 ngôi sao", "Một ngày nhớ nhớ bóng hình em trái tim mong chờ những phút...", true));
                break;
            case "California":
                lstSongs.add(new Song("60001", "123 Chia đôi lối về", "Nuốt nước mắt khẽ ôm em ấm lòng...", false));
                lstSongs.add(new Song("60002", "123 Chia tay", "Một là yêu em, hai là bên em, ba là anh chỉ có em...", true));
                break;
        }
        songAdapter = new SongAdapter(getActivity(), lstSongs);
        lsvSongs.setAdapter(songAdapter);
        lsvSongs.setEmptyView(view.findViewById(R.id.emptyElement));
    }

    public void addItemToSpinner(){
        //Đưa dữ liệu các vols vào đây, sắp xếp theo giảm dần, để list All ở trên cùng của spinner.
        //Dựa theo karaokeType.
        List<String> lstVols = new ArrayList<>();
        lstVols.add("All");
        lstVols.add("Vol 59");
        lstVols.add("Vol 58");

        ArrayAdapter<String> volAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, lstVols);
        volAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnSelectVol.setAdapter(volAdapter);
    }
}
