package com.example.karori.menuFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.karori.Adapter.AdapterChangeRiassunti;
import com.example.karori.R;
import com.example.karori.SearchClasses.RicercaEAggiungiActivity;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import me.bastanfar.semicirclearcprogressbar.SemiCircleArcProgressBar;

public class FragmentHome extends Fragment {
    private static final String KEY_INDEX="index";
    private ImageButton mattina;
    private ImageButton pomeriggio;
    private ImageButton sera;
    private int where;
    private TextView totProteineBar;
    private TextView quantoSei;
    private SemiCircleArcProgressBar progressBar;
    private String pasto = "";
    private CircleImageView butun;


    public FragmentHome() {
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            where= savedInstanceState.getInt(KEY_INDEX,0);
        }
        else{
            where=0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        quantoSei= (TextView) view.findViewById(R.id.quantoSei);
        totProteineBar=(TextView) view.findViewById(R.id.numeroProteine);

        ViewPager2 viewPager = view.findViewById(R.id.pager);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        butun = view.findViewById(R.id.changeActivity);

        AdapterChangeRiassunti adapter = new AdapterChangeRiassunti(this);
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //PER COLORARE IL TESTO
                /* tablayout.setTabTextColors(Color.GRAY, Color.GREEN); // set the tab text colors for the both states of the tab.*/
                viewPager.setCurrentItem(tab.getPosition());
                String Fronsblix = Integer.toString(tab.getPosition());
                butun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), RicercaEAggiungiActivity.class);
                        intent.putExtra("pasto", Fronsblix);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //nothing..
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                String fronsblix = Integer.toString(tab.getPosition());
                butun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), RicercaEAggiungiActivity.class);
                        intent.putExtra("pasto",fronsblix);
                        startActivity(intent);
                    }
                });
            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        //barra inizializzazione
        progressBar=(SemiCircleArcProgressBar) view.findViewById(R.id.semiCircleArcProgressBar);


        //SERVE PER CAMBIARE ACTIVITY ALLA PRESSIONE DEL BOTTONE, E POI TRAMITE IL WHERE CHE SAREBBE
        //UN VALORE PER INDICARE SE ERO SU COLAZ/PRANZ... SCELGO IL FRAGMENT DA METTERE SULLA ACTIVITY

        //per capire quanto siamo di percentuale sulla barra delle nostre proteine
        progressBarUpdate(200, 300);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, where);
    }

    public  void progressBarUpdate(float totProteineAssunte, float totProteine){
        if(totProteineAssunte>totProteine){
            totProteineBar.setText(">100 %");
            quantoSei.setText(String.valueOf(totProteineAssunte)+" / "+ String.valueOf(totProteine));
            progressBar.setPercent(100);
        }
        else{
            totProteineBar.setText(String.valueOf((totProteineAssunte/totProteine)*100)+" %");
            quantoSei.setText(String.valueOf(totProteineAssunte)+" / "+ String.valueOf(totProteine));
            progressBar.setPercent((int)((totProteineAssunte/totProteine)*100));
        }

    }
}
