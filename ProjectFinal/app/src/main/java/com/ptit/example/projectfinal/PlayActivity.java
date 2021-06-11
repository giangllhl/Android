package com.ptit.example.projectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import jp.wasabeef.blurry.Blurry;

public class PlayActivity extends AppCompatActivity {
    ImageView imageAnhnen;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        anhxa();
        setUp();
        setClick();
    }
    private void init(){

    }
    private void anhxa(){
        imageAnhnen = findViewById(R.id.imgNen);
    }
    private void setUp(){

        setAnhNen(R.drawable.lactroi);
        batdauchoinhac(R.raw.lactroi);
    }
    private void setClick(){

    }
    private void setAnhNen(int i){
        Blurry.Composer with = Blurry.with(this);
        with.radius(10);
        with.sampling(8);
        with.color(Color.argb(66, 255, 255, 0));
        with.async();
    }

    private void batdauchoinhac(int bainhac){
        if (player == null) {
            player = MediaPlayer.create(this, bainhac);
        }else {
            player.stop();
            player.release();
            player = MediaPlayer.create(this, bainhac);
        }
        player.start();
    }
}

