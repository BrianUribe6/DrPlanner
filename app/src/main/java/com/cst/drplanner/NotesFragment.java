package com.cst.drplanner;

import android.content.Context;
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

public class NotesFragment extends Fragment {
    private ArrayList<String> mNoteTitle = new ArrayList<>();
    private ArrayList<String> mNoteBody = new ArrayList<>();

    NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(mNoteTitle, mNoteBody, getActivity());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        //Floating Action Button in fragment notes
        FloatingActionButton fabtnCreateNote = view.findViewById(R.id.fabtnCreateNote);
//
//        //Recycler view in fragment notes
//        RecyclerView rviewNotes = view.findViewById(R.id.rviewNotes);
        initRecyclerView(view);
        fabtnCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
                adapter.notifyItemInserted(mNoteTitle.size()+1);
            }
        });
        return view;
    }

    private void addNote(){
        mNoteTitle.add("This is a test");
        mNoteBody.add("This is definitely a test");
    }

    private void initRecyclerView(View view){
        RecyclerView rviewNotes = view.findViewById(R.id.rviewNotes);
        rviewNotes.setAdapter(adapter);
        rviewNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



}
