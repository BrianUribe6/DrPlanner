package com.cst.drplanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    static List<NotesBuilder> notesList = new ArrayList<>();
    static NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(notesList);
    private RecyclerView notesRecyler;
    private Context context;
    private static final String TAG = "NotesFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        context = view.getContext();
        prepareNotes();
        //Floating Action Button in fragment notes
        FloatingActionButton fabtnCreateNote = view.findViewById(R.id.fabtnCreateNote);
//
//        //Recycler view in fragment notes
        notesRecyler= view.findViewById(R.id.rviewNotes);
        notesRecyler.setAdapter(adapter);
        initRecyclerView(view);
        fabtnCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotesEditor.class);
                startActivity(i);
            }
        });

        return view;
    }


    public void addNote(String title, String body){
        boolean notRepeated = true;
        for (int i = 0; i < notesList.size(); i++) {
            if (notesList.get(i).getTitle().equals(title)){
                notRepeated = false;
            }
        }
        if (notRepeated) {
            NotesBuilder note = new NotesBuilder(title, body);
            notesList.add(note);
            adapter.notifyDataSetChanged();
        }
    }
///data/user/0/com.cst.drplanner/files
//    public String Open(String fileName) {
//
//        //Get the text file
//        String directory = context.getFilesDir().getAbsolutePath();
//
//        //Read text from file
//        File textFile = new File(directory, fileName);
//
//        //Read text from file
//        StringBuilder text = new StringBuilder();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(textFile));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                text.append(line);
//                text.append('\n');
//            }
//            br.close();
//        } catch (IOException e) {
//            Toast.makeText(getContext(), "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
//        }
//        return text.toString();
//    }
    public String Open(String fileName) {
        String content = "";
        try {
            InputStream in = context.openFileInput(fileName);
            if ( in != null) {
                InputStreamReader tmp = new InputStreamReader( in );
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                } in .close();

                content = buf.toString();
            }
        } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
            Log.d(TAG, "Exception: " + t.toString());
            Toast.makeText(getContext(), "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

        return content;
    }

    private void prepareNotes() {
        File directory;
        directory = context.getFilesDir();
        File[] files = directory.listFiles();
        String filename;
        for (int f = 1; f < files.length; f++) {
            filename = files[f].getName();
            //removing the .txt extension text
            filename = filename.substring(0, filename.length() - 4);
            NotesBuilder note = new NotesBuilder(filename, Open(filename));
//            notesList.add(note);
        }
    }
    private void initRecyclerView(View view){
        RecyclerView rviewNotes = view.findViewById(R.id.rviewNotes);
        rviewNotes.setAdapter(adapter);
        rviewNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



}
