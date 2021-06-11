package com.ptit.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ptit.example.projectfinal.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewSongActivity extends AppCompatActivity {
    Spinner spinner;
    CheckBox checkBox;
    EditText title, singer;
    Button create, cancel;
    SQLiteSongHelper sqLiteSongHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_song);
        sqLiteSongHelper = new SQLiteSongHelper(this);
        initView();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String img = spinner.getSelectedItem().toString();
                String t = title.getText().toString();
                String s = singer.getText().toString();
                String fav = checkBox.isChecked() ? "1" : "0";
                Song song = new Song(img , t, s, fav);
                sqLiteSongHelper.addSong(song);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        spinner = findViewById(R.id.spImg);
        title = findViewById(R.id.edttitle);
        singer = findViewById(R.id.edtsinger);
        checkBox = findViewById(R.id.cbFav);
        create = findViewById(R.id.btnCreate);
        cancel = findViewById(R.id.btnCancel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.song_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}