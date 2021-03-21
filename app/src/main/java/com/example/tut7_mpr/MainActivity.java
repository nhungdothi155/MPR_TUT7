package com.example.tut7_mpr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tut7_mpr.note_recycleView.NoteRecyleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String content;
    private DBManager noteManager;
    private List<Note> noteList;
    private TextView noteEdit;
    private NoteRecyleView myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteManager = new DBManager(this,null,null,3);

        noteList = noteManager.showNotes();
        RecyclerView listNotes = (RecyclerView) findViewById(R.id.recycleView_id);

        myAdapter = new NoteRecyleView(noteList,this,MainActivity.this);

        listNotes.setLayoutManager(new LinearLayoutManager(this));
        listNotes.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         if(item.getItemId()==R.id.add){
             Intent intent = new Intent(this, AddFunction.class);
             intent.putExtra("CONTENT",content);
             startActivityForResult(intent,101);
             return true;
         }
         else {
             return super.onOptionsItemSelected(item);
         }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){

            if(resultCode== Activity.RESULT_OK){
                content = data.getSerializableExtra("CONTENT").toString();
                   Note n = new Note();
                   n.setContent(content);
                    noteManager.addNote(n);
                   noteList.clear();

                   noteList.addAll(noteManager.showNotes());
                   myAdapter.notifyDataSetChanged();

                   Toast.makeText(this, content, Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode==102){
            if(resultCode==Activity.RESULT_OK){
                Note note= (Note) data.getSerializableExtra("INSTANCE");
                myAdapter.updateAdapter(note);
                myAdapter.notifyDataSetChanged();
                noteList.set(note.getId(),note);
                Toast.makeText(this,noteList.size() +"",Toast.LENGTH_LONG).show();

            }

        }
    }


}