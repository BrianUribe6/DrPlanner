package com.cst.drplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class NotesFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);


        //Floating Action Button in fragment notes
        FloatingActionButton fabtnCreateNote = view.findViewById(R.id.fabtnCreateNote);

        //Recycler view in fragment notes
        RecyclerView rviewNotes = view.findViewById(R.id.rviewNotes);
        rviewNotes.setHasFixedSize(true);

     //   layoutManager = new LinearLayout();
        rviewNotes.setLayoutManager(layoutManager);

        return view;
    }



}
