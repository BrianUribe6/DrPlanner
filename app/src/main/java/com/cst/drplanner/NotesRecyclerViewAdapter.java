package com.cst.drplanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NotesRecyclerViewAdapter  extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>{
    private ArrayList<String> mNoteTitle;
    private ArrayList<String> mNoteBody;
    private Context mContext;

    public NotesRecyclerViewAdapter(ArrayList<String> mNoteTitle, ArrayList<String> mNoteBody, Context mContext) {
        this.mNoteTitle = mNoteTitle;
        this.mNoteBody = mNoteBody;
        this.mContext = mContext;
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
    viewHolder.noteTitle.setText(mNoteTitle.get(i));
    viewHolder.noteBody.setText(mNoteBody.get(i));
    }

    @Override
    public int getItemCount() {
        return mNoteTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteTitle;
        TextView noteBody;
        LinearLayout notesParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.txt_note_title);
            noteBody = itemView.findViewById(R.id.txt_note_body);
            notesParentLayout = itemView.findViewById(R.id.notes_parent_layout);
        }


    }

}
