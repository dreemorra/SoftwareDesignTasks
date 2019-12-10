package com.example.lab3;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> values;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView tagView;
        public TextView descriptionView;
        public ViewHolder(View view) {
            super(view);
            titleView = view.findViewById(R.id.title);
            tagView = view.findViewById(R.id.tag);
            descriptionView = view.findViewById(R.id.description);
        }
    }

    public NoteAdapter(List<Note> values) {
        this.values = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notelist, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleView.setText(values.get(position).Title);
//        holder.tagView.setText(values.get(position).Tag);
        holder.descriptionView.setText(values.get(position).Description);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}