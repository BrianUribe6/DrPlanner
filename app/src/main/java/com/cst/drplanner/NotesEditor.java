package com.cst.drplanner;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStreamWriter;

public class NotesEditor extends AppCompatActivity {
    private EditText txtNoteTitle;
    private EditText txtNoteBody;
    private FloatingActionButton fabtnSave;
    //MainActivity.SelectedBundle selectedBundle;

   /* public void setOnBundleSelected(MainActivity.SelectedBundle selectedBundle) {
        this.selectedBundle = selectedBundle;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        txtNoteTitle = findViewById(R.id.txt_EDITOR_note_title);
        txtNoteBody = findViewById(R.id.txt_EDITOR_note_body);
        fabtnSave = findViewById(R.id.fabtn_save);

       //FragmentManager manager = getSupportFragmentManager();
      // final android.support. transaction = manager.beginTransaction();
       //final NotesFragment nFragment = new NotesFragment();

       fabtnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               /*Bundle bundle = new Bundle();
               bundle.putString("title", txtNoteTitle.getText().toString());
               bundle.putString("body", txtNoteBody.getText().toString());

               // set MyFragment Arguments
               NotesFragment myObj = new NotesFragment();
               myObj.setArguments(bundle);
               nFragment.setArguments(bundle);
               transaction.add(R.id.fragment_container, nFragment);
               transaction.commit();*/


               //nFragment.setArguments(data);


           }
       });

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




