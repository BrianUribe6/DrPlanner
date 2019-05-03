package com.cst.drplanner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotesEditor extends AppCompatActivity {
    private EditText txtNoteTitle;
    private EditText txtNoteBody;
    private Intent intent = getIntent();
    private String title, content;
    private FloatingActionButton fabSaveNote;
    private static final String TAG = "NotesEditor";
    public NotesEditor() {
    }

    public NotesEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        txtNoteTitle = findViewById(R.id.txt_EDITOR_note_title);
        txtNoteBody = findViewById(R.id.txt_EDITOR_note_body);
        fabSaveNote = findViewById(R.id.fabtn_save);

        txtNoteTitle.setText(title);
        txtNoteBody.setText(content);

        fabSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNoteTitle.getText().toString().isEmpty()){
                    Toast.makeText(NotesEditor.this, "You must insert a title", Toast.LENGTH_SHORT).show();
                }else {
                save(txtNoteTitle.getText().toString() + ".txt");
                    new NotesFragment().addNote(txtNoteTitle.getText().toString(), txtNoteBody.getText().toString());
                    NotesFragment.adapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
    }
    public void save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(txtNoteBody.getText().toString());
            out.close();
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}




