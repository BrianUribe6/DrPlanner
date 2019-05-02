package com.cst.drplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStreamWriter;

public class NotesEditor extends AppCompatActivity {
    private EditText txtNoteTitle;
    private EditText txtNoteBody;
    private String title, content;

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
        txtNoteTitle = findViewById(R.id.txt_EDITOR_note_title);
        txtNoteBody = findViewById(R.id.txt_EDITOR_note_body);
    }

    public void Save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(txtNoteTitle.toString());
            out.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean FileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public String getNoteTitle(){
        return txtNoteTitle.toString();
    }

    public String getNoteBody(){
        return txtNoteBody.toString();
    }


}




