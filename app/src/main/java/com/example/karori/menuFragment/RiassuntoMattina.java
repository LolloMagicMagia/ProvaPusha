package com.example.karori.menuFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.karori.R;

public class RiassuntoMattina extends Fragment {
    private static final String KEY_GRASSI = "GRASSIM";
    private static final String KEY_SATURI = "SATURIM";
    private static final String KEY_CARBOIDRATI = "CARBOIDRATIM";
    private static final String KEY_CALORIE = "CALORIEM";

    private TextView tgrassi;
    private TextView tsaturi;
    private TextView tcarboidrati;
    private TextView tcalorie;



    public RiassuntoMattina() {
        // Required empty public constructor
    }

    public static RiassuntoMattina newInstance() {
        return new RiassuntoMattina();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_riassunto_mattina, container, false);
        tgrassi= (TextView) view.findViewById(R.id.grassi);
        tsaturi= (TextView) view.findViewById(R.id.Saturi);
        tcalorie= (TextView) view.findViewById(R.id.calorie);
        tcarboidrati= (TextView) view.findViewById(R.id.carboidrati);


        //mattina, dovrà andare a prendere i valori nel database inizialmente
        setValoriRiassuntivi(savedInstanceState);



        // Inflate the layout for this fragment
        return view;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_CALORIE, "40");
        savedInstanceState.putString(KEY_SATURI, "20");
        savedInstanceState.putString(KEY_CARBOIDRATI, "20");
        savedInstanceState.putString(KEY_GRASSI, "10");
    }

    private void setValoriRiassuntivi(Bundle savedInstanceState){
        //vado nel databese
        if(savedInstanceState==null) {
            tgrassi.setText("Grassi: " + "20");
            tsaturi.setText("Saturi: " + "70");
            tcarboidrati.setText("Caboidrati: " + "30");
            tcalorie.setText("Calorie: " + "40");
        }
        //qua invece c'ho il bundle che va a salvare tutto
        else{
            tgrassi.setText("Grassi: " + savedInstanceState.get(KEY_GRASSI));
            tsaturi.setText("Saturi: " + savedInstanceState.get(KEY_SATURI));
            tcarboidrati.setText("Caboidrati: " + savedInstanceState.get(KEY_CARBOIDRATI));
            tcalorie.setText("Calorie: " + savedInstanceState.get(KEY_CALORIE));
        }
    }
}
