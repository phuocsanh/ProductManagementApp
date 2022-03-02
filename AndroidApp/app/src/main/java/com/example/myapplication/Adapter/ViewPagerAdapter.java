package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Fragment.FragmentDoanhThu;
import com.example.myapplication.Fragment.FragmentThongKe;
import com.example.myapplication.Fragment.FragmentTop;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentTop();
            case 1:
                return  new FragmentDoanhThu();
            default:
                return new FragmentDoanhThu();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int possiotion){
        String title="";
        switch (possiotion){
            case 0: title = "Top 10";
                break;
            case 1: title = "Doanh Thu";
                break;
        }
        return  title;
    }

}
