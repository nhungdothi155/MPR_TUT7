package com.example.tut7_mpr.note_recycleView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tut7_mpr.AddFunction;
import com.example.tut7_mpr.DBManager;
import com.example.tut7_mpr.MainActivity;
import com.example.tut7_mpr.Note;
import com.example.tut7_mpr.R;
import com.example.tut7_mpr.UpdateFunction;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyleView extends RecyclerView.Adapter<NoteRecyleView.ViewHolder>  {


    private List<Note> noteList =new ArrayList<Note>();
    private Activity activity;
    private Context context;

    public NoteRecyleView(List<Note> noteList, Context context,Activity activity) {
        this.activity= activity;
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.note_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.content.setText(noteList.get(position).getContent());
     holder.content.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, UpdateFunction.class);
            Note note  = new Note();
             note.setContent(holder.content.getText().toString());
             note.setId(position);
             intent.putExtra("INSTANCE",note);
             activity.startActivityForResult(intent,102);


         }
     });
     holder.content.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {

            new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure")
                    .setMessage("Do you want do delete this note")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DBManager db = new DBManager(context,null,null,3);
                            // update adapter element like NAME, EMAIL e.t.c. here
                            db.deleteNote(noteList.get(position).getId());
                            noteList.remove(position);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("NO",null)
                    .show();
             return true;

         }

     });

    }
    public void updateAdapter(Note note){
        DBManager db = new DBManager(context,null,null,3);
        // update adapter element like NAME, EMAIL e.t.c. here
        db.updateNote(note);
        // then in order to refresh the views notify the RecyclerView
//        noteList.set(note.getId(),note);
        notifyDataSetChanged();


    }
    public void addAdapter(Note note){
        DBManager db = new DBManager(context,null,null,3);
        // update adapter element like NAME, EMAIL e.t.c. here
        db.addNote(note);
        // then in order to refresh the views notify the RecyclerView
        notifyDataSetChanged();


    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.editNote);

        }
    }
}
