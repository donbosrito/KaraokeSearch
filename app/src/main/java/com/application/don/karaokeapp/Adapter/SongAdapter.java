package com.application.don.karaokeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.don.karaokeapp.DTO.SongDTO;
import com.application.don.karaokeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don on 5/13/16.
 */
public class SongAdapter extends ArrayAdapter<SongDTO> {
    private List<SongDTO> items;

    public SongAdapter(Context context, List<SongDTO> list) {
        super(context, 0, list);
        items = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SongDTO item = items.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item_layout, parent, false);
        }
        // Lookup view for data population
        TextView itemCode = (TextView) convertView.findViewById(R.id.txtCode);
        TextView itemName = (TextView) convertView.findViewById(R.id.txtName);
        TextView itemLyric = (TextView) convertView.findViewById(R.id.txtLyric);
        CheckBox itemFav = (CheckBox) convertView.findViewById(R.id.cbxFav);
        // Populate the data into the template view using the data object
        itemCode.setText(item.get_code());
        itemName.setText(item.get_name());
        itemLyric.setText(item.get_lyric());
        itemFav.setChecked(item.is_favorite());
        // Return the completed view to render on screen
        return convertView;
    }
}
