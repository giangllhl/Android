package com.ptit.example.projectfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ptit.example.projectfinal.fragment.FavoriteFragment;
import com.ptit.example.projectfinal.fragment.HomeFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new FavoriteFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String s = "";
        switch (position){
            case 0:
                s = "TRANG CHỦ";
                break;
            case 1:
                s = "NHẠC YÊU THÍCH";
                break;
        }
        return s;
    }
}
