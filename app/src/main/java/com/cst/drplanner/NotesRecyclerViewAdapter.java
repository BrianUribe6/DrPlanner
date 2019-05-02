package com.cst.drplanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter  extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {

    private List<NotesBuilder> notesList;

    public NotesRecyclerViewAdapter(List<NotesBuilder> notesList) {
        this.notesList = notesList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_note_entry,
                viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotesBuilder note = notesList.get(i);
        viewHolder.title.setText(note.getTitle());
        viewHolder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title, content;
        LinearLayout notesParentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_note_title);
            content = itemView.findViewById(R.id.txt_note_body);
            notesParentLayout = itemView.findViewById(R.id.notes_parent_layout);
        }


    }

}
