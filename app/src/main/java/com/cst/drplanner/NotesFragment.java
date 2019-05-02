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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    private List<NotesBuilder> notesList = new ArrayList<>();

    private NotesRecyclerViewAdapter adapter;
    private RecyclerView notesRecyler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        //Floating Action Button in fragment notes
        FloatingActionButton fabtnCreateNote = view.findViewById(R.id.fabtnCreateNote);
//
//        //Recycler view in fragment notes
        adapter = new NotesRecyclerViewAdapter(notesList);
        RecyclerView rviewNotes = view.findViewById(R.id.rviewNotes);
        rviewNotes.setAdapter(adapter);
        initRecyclerView(view);
        fabtnCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotesEditor.class);
                startActivity(i);
//              ((Activity) getActivity()).overridePendingTransition(0, 0);

                addNote("Sup", "Hello?");
//                adapter.notifyItemInserted(notesList.size()+1);
            }
        });

        return view;
    }

    public void addNote(String title, String body){
       NotesBuilder note = new NotesBuilder(title, body);
       notesList.add(note);
       adapter.notifyItemInserted(notesList.size()+1);
    }

    private void initRecyclerView(View view){
        RecyclerView rviewNotes = view.findViewById(R.id.rviewNotes);
        rviewNotes.setAdapter(adapter);
        rviewNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



}
