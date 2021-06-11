package com.ptit.example.projectfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ptit.example.projectfinal.DetailActivity;
import com.ptit.example.projectfinal.R;
import com.ptit.example.projectfinal.fragment.HomeFragment;
import com.ptit.example.projectfinal.model.Song;

import java.util.ArrayList;
import java.util.List;

import io.grpc.internal.SharedResourceHolder;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    Context mContext;
    public List<Song> listSong = new ArrayList<>();

    public SongAdapter(Context context, List<Song> list) {
        this.mContext = context;
        this.listSong = list;
    }

    public void setData(List<Song> list) {
        this.listSong = list;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
//        return new SongViewHolder(view);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_song, parent, false);
        SongViewHolder songViewHolder = new SongViewHolder(view);
        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, final int position) {
        Song song = listSong.get(position);
        int i = 0, j = 0;

        if (song == null) {
            return;
        } else {
            if (listSong.get(position).getImage().equals("Lac troi")) {
                i = (int) R.drawable.lactroi;
            }
            if (listSong.get(position).getImage().equals("Muon roi ma sao con")) {
                i = (int) R.drawable.mrmsc;
            }
            if (listSong.get(position).getImage().equals("Em cua ngay hom qua")) {
                i = (int) R.drawable.ecnhq;
            }
            holder.img.setImageResource(i);
            holder.title.setText(song.getTitle());
            holder.singer.setText(song.getSinger());
            if (listSong.get(position).getFavorite().equals("0")) {
                j = (int) R.drawable.tim;
            }
            if (listSong.get(position).getFavorite().equals("1")) {
                j = (int) R.drawable.timdo;
            }
            holder.fav.setImageResource(j);
//            HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("HomeFragment");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("id", listSong.get(position).getId());
                    intent.putExtra("image", listSong.get(position).getImage());
                    intent.putExtra("title", listSong.get(position).getTitle());
                    intent.putExtra("singer", listSong.get(position).getSinger());
                    intent.putExtra("favorite", listSong.get(position).getFavorite());
                    mContext.startActivity(intent);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        if (listSong != null) {
            return listSong.size();
        }
        return 0;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, singer;
        ImageView fav;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCard);
            title = itemView.findViewById(R.id.titleCard);
            singer = itemView.findViewById(R.id.singerCard);
            fav = itemView.findViewById(R.id.favCard);
        }
    }
}
