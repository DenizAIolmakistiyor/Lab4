package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;


public class MainActivity2 extends AppCompatActivity {

int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText editText = findViewById(R.id.edtContext);

        Intent intent = getIntent();
        int noteID = intent.getIntExtra("noteId", -1);
        if (noteID != -1) {
        editText.setText(MainActivity.notes.get(noteID)); }
        else {

            MainActivity.notes.add("");
            noteID = MainActivity.notes.size() -1;
            MainActivity.arrayAdapter.notifyDataSetChanged();


        }

        int finalNoteID = noteID;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                MainActivity.notes.set(finalNoteID, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.lab4", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}