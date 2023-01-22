package com.example.karori.menuFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.karori.R;

public class RiassuntoSera extends Fragment {
    private TextView tgrassis;
    private TextView tsaturis;
    private TextView tcarboidratis;
    private TextView tcalories;
    private static final String KEY_GRASSIS = "GRASSIS";
    private static final String KEY_SATURIS = "SATURIS";
    private static final String KEY_CARBOIDRATIS = "CARBOIDRATIS";
    private static final String KEY_CALORIES = "CALORIES";

    public RiassuntoSera() {
        // Required empty public constructor
    }

    public static RiassuntoSera newInstance() {
        return new RiassuntoSera();
    }

    public static RiassuntoSera newInstance(String param1, String param2) {
        RiassuntoSera fragment = new RiassuntoSera();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riassunto_sera, container, false);
        tgrassis= (TextView) view.findViewById(R.id.grassiN);
        tsaturis= (TextView) view.findViewById(R.id.SaturiN);
        tcalories= (TextView) view.findViewById(R.id.calorieN);
        tcarboidratis= (TextView) view.findViewById(R.id.carboidratiN);
        //sera, dovrà andare a prendere i valori nel database inizialmente
        setValoriRiassuntivi(savedInstanceState);

        return view;
    }

    private void setValoriRiassuntivi(Bundle savedInstanceState){
        //vado nel databese
        if(savedInstanceState==null) {
            tgrassis.setText("Grassi: " + "20");
            tsaturis.setText("Saturi: " + "60");
            tcarboidratis.setText("Caboidrati: " + "30");
            tcalories.setText("Calorie: " + "30");
        }
        else{
            tgrassis.setText("Grassi: " + savedInstanceState.get(KEY_GRASSIS));
            tsaturis.setText("Saturi: " + savedInstanceState.get(KEY_SATURIS));
            tcarboidratis.setText("Caboidrati: " + savedInstanceState.get(KEY_CARBOIDRATIS));
            tcalories.setText("Calorie: " + savedInstanceState.get(KEY_CALORIES));
        }
    }
}
