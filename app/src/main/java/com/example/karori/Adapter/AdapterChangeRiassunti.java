package com.example.karori.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.karori.menuFragment.RiassuntoMattina;
import com.example.karori.menuFragment.RiassuntoPomeriggio;
import com.example.karori.menuFragment.RiassuntoSera;

public class AdapterChangeRiassunti extends FragmentStateAdapter {
    public AdapterChangeRiassunti(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        if(position==0){
            return RiassuntoMattina.newInstance();
        }
        else if(position==1){
            return RiassuntoPomeriggio.newInstance();
        }
        else {
            return RiassuntoSera.newInstance();
        }
        //se devo passare un valore
            /*TuoFragment tuoFragment = new TuoFragment();
            Bundle args = new Bundle();
            args.putInt(“chiave", valoreRicevutoComeArgomento);
            tuoFragment.setArguments(args);
            return tuoFragment;*/
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
