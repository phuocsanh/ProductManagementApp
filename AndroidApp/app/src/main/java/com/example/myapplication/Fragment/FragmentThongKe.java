package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.ViewPagerAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentThongKe extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        tabLayout =(TabLayout)view.findViewById(R.id.tab_layout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager()
                , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        selectPage(0);
        return view;
    }

    void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }
}
