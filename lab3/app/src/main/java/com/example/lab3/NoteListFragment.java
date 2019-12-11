package com.example.lab3;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    public interface OnItemClickListener {
        void onItemClick(Note item, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(Note item, int position);
    }

    private final List<Note> items;
    private final OnItemClickListener listener;
    private final OnItemLongClickListener longClickListener;

    public NoteAdapter(List<Note> items, OnItemClickListener listener, OnItemLongClickListener longListener) {
        this.items = items;
        this.listener = listener;
        this.longClickListener = longListener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notelist, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener, longClickListener);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;
        public TextView tagView;
        public TextView descriptionView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            tagView = itemView.findViewById(R.id.tag);
            descriptionView = itemView.findViewById(R.id.description);
        }

        public void bind(final Note item, final OnItemClickListener listener, final OnItemLongClickListener longClickListener) {
            titleView.setText(item.Title);
            tagView.setText(item.Tags);
            descriptionView.setText(item.Description);
            itemView.setOnClickListener(v -> listener.onItemClick(item, getAdapterPosition()));
            itemView.setOnLongClickListener(v -> longClickListener.onItemLongClick(item, getAdapterPosition()));
        }
    }
}