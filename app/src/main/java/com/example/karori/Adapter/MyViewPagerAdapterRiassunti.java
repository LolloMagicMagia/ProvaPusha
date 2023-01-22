package com.example.karori.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.karori.menuFragment.RiassuntoMattina;
import com.example.karori.menuFragment.RiassuntoPomeriggio;
import com.example.karori.menuFragment.RiassuntoSera;

public class MyViewPagerAdapterRiassunti extends FragmentStateAdapter {
    int mNumOfTabs;

    public MyViewPagerAdapterRiassunti(@NonNull FragmentActivity fragmentActivity, int numOfTabs) {
        super(fragmentActivity);
        this.mNumOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new RiassuntoMattina();
            case 1:
                return new RiassuntoPomeriggio();
            case 2:
                return new RiassuntoSera();
            default:
                return new RiassuntoMattina();

        }
    }

    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}
