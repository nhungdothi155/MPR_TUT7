package com.example.tut7_mpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tut7_mpr.note_recycleView.NoteRecyleView;

public class UpdateFunction extends AppCompatActivity {
    private EditText updateNote;
    private NoteRecyleView noteRecyleView;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_function);
        dbManager = new DBManager(getApplicationContext(), null,null,3);
        Intent intent = getIntent();
        Note note = (Note) intent.getExtras().getSerializable("INSTANCE");

        updateNote = findViewById(R.id.update_note);
        updateNote.setText(note.getContent());
        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setContent(updateNote.getText().toString());
                intent.putExtra("INSTANCE",note);
                setResult(RESULT_OK,intent);
                finish();


            }
        });

    }
}