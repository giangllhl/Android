package com.ptit.example.projectfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ptit.example.projectfinal.adapter.SongAdapter;
import com.ptit.example.projectfinal.fragment.HomeFragment;
import com.ptit.example.projectfinal.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context;
    private TabLayout layout;
    private ViewPager viewPager;
    private FloatingActionButton actionButton;
    private TextView keySearch;
    SongAdapter songAdapter;
    private RecyclerView recyclerView;
    private List<Song> list;
    SQLiteSongHelper sqLiteSongHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        initView();
        sqLiteSongHelper = new SQLiteSongHelper(this);



        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewSongActivity.class);
                startActivity(intent);
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        layout.setupWithViewPager(viewPager);
    }

    private void initView() {
        layout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        actionButton = findViewById(R.id.fab);
        keySearch = findViewById(R.id.key);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void search(View view) {
//        String key = keySearch.getText().toString();
        recyclerView = findViewById(R.id.recycleItem);
//        HomeFragment fragment = new HomeFragment();

        String key = keySearch.getText().toString();
        list = sqLiteSongHelper.searchByTitle(key);
        songAdapter = new SongAdapter(context, list);
        songAdapter.setData(list);
        recyclerView.setAdapter(songAdapter);
    }
}