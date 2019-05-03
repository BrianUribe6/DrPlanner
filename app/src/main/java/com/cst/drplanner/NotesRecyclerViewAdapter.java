package com.cst.drplanner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter  extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "NotesRecyclerViewAdapte";
    private List<NotesBuilder> notesList;
    private String title;
    private boolean deleted;

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        title =  notesList.get(viewHolder.getAdapterPosition()).getTitle();
        NotesBuilder note = notesList.get(i);
        viewHolder.title.setText(note.getTitle());
        viewHolder.content.setText(note.getContent());
        viewHolder.notesParentLayout.setOnClickListener(new View.OnClickListener() {

            String noteContent = open(title + ".txt");
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), NotesEditor.class);
                    i.putExtra("title",title);
                    i.putExtra("content",noteContent);
                    view.getContext().startActivity(i);
            }
        });
        viewHolder.notesParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //get the path to the file
                File dir = view.getContext().getFilesDir();
                File file = new File(dir, title + ".txt");
                deleted = file.delete();
                if (deleted) {
                    int position = viewHolder.getAdapterPosition();
                    notesList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(view.getContext(), "Note was successfully deleted.", Toast.LENGTH_SHORT).show();
                    return deleted;
                }
                return false;
            }
        });
    }
    private String open(String fileName) {

        //Get the text file
        String directory = "//data//user//0//com.cst.drplanner//files";

        //Read text from file
        File textFile = new File(directory, fileName);

        //Read text from file
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(textFile));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            Log.d(TAG, "Open: "+e.toString());
        }
        return text.toString();
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
