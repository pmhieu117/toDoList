package com.example.todolist.adapter;

import com.example.todolist.fragment.DoneFragment;
import com.example.todolist.fragment.HomeFragment;
import com.example.todolist.fragment.ProfileFragment;
import com.example.todolist.fragment.NotDoneYetFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    public ViewpagerAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: return new NotDoneYetFragment();
            case 2: return new DoneFragment();
            case 3: return new ProfileFragment();
            default: return new HomeFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
