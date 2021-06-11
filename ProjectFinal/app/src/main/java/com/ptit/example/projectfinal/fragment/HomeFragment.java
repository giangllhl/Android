package com.ptit.example.projectfinal.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ptit.example.projectfinal.R;
import com.ptit.example.projectfinal.SQLiteSongHelper;
import com.ptit.example.projectfinal.adapter.SongAdapter;
import com.ptit.example.projectfinal.model.Song;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private List<Song> list;
    SongAdapter songAdapter;
    SQLiteSongHelper sqLiteSongHelper;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycleItem);
        list = new ArrayList<>();
        sqLiteSongHelper = new SQLiteSongHelper(getContext());
        list=sqLiteSongHelper.getAll();
        System.out.println(list.size());
        songAdapter = new SongAdapter(getContext(), list);
        songAdapter.setData(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(songAdapter);
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Song> list = sqLiteSongHelper.getAll();
        songAdapter.listSong = list;
        songAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(songAdapter);
    }
}