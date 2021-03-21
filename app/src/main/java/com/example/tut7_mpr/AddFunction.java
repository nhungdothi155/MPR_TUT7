package com.example.tut7_mpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFunction extends AppCompatActivity {
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);
        Intent intent = getIntent();
        text = findViewById(R.id.add_function);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("CONTENT",text.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });


    }
}