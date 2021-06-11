package com.ptit.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.ptit.example.projectfinal.model.Song;

public class DetailActivity extends AppCompatActivity {
    Spinner spinner;
    EditText title, singer;
    CheckBox fav;
    Button update, delete;
    SQLiteSongHelper sqLiteSongHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        sqLiteSongHelper = new SQLiteSongHelper(getApplicationContext());
        initView();
        setIntentValue();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image = spinner.getSelectedItem().toString();
                String t = title.getText().toString();
                String s = singer.getText().toString();
                String f = fav.isChecked() ? "1" : "0";
                String id = (getIntent().getStringExtra("id"));
                Song song =new Song(id, image, t, s, f);
                sqLiteSongHelper.updateSong(song);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (getIntent().getStringExtra("id"));
                sqLiteSongHelper.deleteSong(id);
                finish();
            }
        });
    }



    private void initView() {
        spinner = findViewById(R.id.sp);
        title = findViewById(R.id.etTitle);
        singer = findViewById(R.id.etSinger);
        fav = findViewById(R.id.cbFav);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
    }

    private void setIntentValue() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.song_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(getIntent().getStringExtra("image"));
        spinner.setSelection(spinnerPosition);

        title.setText(getIntent().getStringExtra("title"));
        singer.setText(getIntent().getStringExtra("singer"));
        fav.setChecked(getIntent().getStringExtra("favorite").equals("1") );
    }
}