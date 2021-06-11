package com.ptit.example.projectfinal.fragment;

import android.os.Bundle;

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


public class FavoriteFragment extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private List<Song> list;
    SongAdapter songAdapter;
    SQLiteSongHelper sqLiteSongHelper;

    public FavoriteFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
    }

    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_favorite, container, false);
        v =  inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycleItemFav);
        list = new ArrayList<>();
        sqLiteSongHelper = new SQLiteSongHelper(getContext());
        list=sqLiteSongHelper.getByFav();
//        System.out.println(list.size());
        songAdapter = new SongAdapter(getContext(), list);
        songAdapter.setData(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(songAdapter);
        return  v;
    }

    public void onResume() {
        super.onResume();
        List<Song> list = sqLiteSongHelper.getByFav();
        songAdapter.listSong = list;
        songAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(songAdapter);
    }
}