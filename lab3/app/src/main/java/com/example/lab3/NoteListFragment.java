package com.example.lab3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        return view;
    }
}

class NoteAdapter extends ArrayAdapter<Note> {

    private final Context context;
    private final Note[] values;

    public NoteAdapter(Context context, Note[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public Note getItem(int position){
        return values[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_notelist, container, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        TextView tagView = (TextView) convertView.findViewById(R.id.tag);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.description);
        titleView.setText(values[position].Title);
        tagView.setText(values[position].Tag);
        descriptionView.setText(values[position].Description);
        return convertView;
    }
}