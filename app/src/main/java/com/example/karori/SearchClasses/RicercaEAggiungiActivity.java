package com.example.karori.SearchClasses;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karori.Adapter.SearchedIngredientsAdapter;
import com.example.karori.Listeners.IngredientIdListener;
import com.example.karori.Listeners.SearchIngredientsListener;
import com.example.karori.Models.SearchIngredientsResponse;
import com.example.karori.R;

public class RicercaEAggiungiActivity extends AppCompatActivity {
    private String pasto;
    private androidx.appcompat.widget.SearchView srchIngredient;
    private TextView txt_select;
    private RecyclerView rec_pasto;

    ProgressDialog dialog;
    RequestManager manager;
    SearchedIngredientsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_aggiungi);
        pasto = getIntent().getStringExtra("pasto");

        txt_select = findViewById(R.id.txt_select);
        int idPasto = Integer.parseInt(pasto);
        if (idPasto == 0)
            txt_select.setText("Hai Selezionato Colazione");
        if (idPasto == 1)
            txt_select.setText("Hai Selezionato Pranzo");
        if (idPasto == 2)
            txt_select.setText("Hai Selezionato Cena");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        manager = new RequestManager(this);

        srchIngredient = findViewById(R.id.srchIngredient);
        srchIngredient.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txt_select.setVisibility(View.GONE);
                rec_pasto.setVisibility(View.VISIBLE);
                dialog.show();
                manager.getIngredientSearchResult(ingredientsListener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        rec_pasto = findViewById(R.id.colazione_cercata);
    }

    private final SearchIngredientsListener ingredientsListener = new SearchIngredientsListener() {
        @Override
        public void didFetch(SearchIngredientsResponse response, String message) {
            dialog.dismiss();
            rec_pasto = findViewById(R.id.colazione_cercata);
            rec_pasto.setHasFixedSize(true);
            rec_pasto.setLayoutManager(new GridLayoutManager(RicercaEAggiungiActivity.this, 1));
            adapter = new SearchedIngredientsAdapter(RicercaEAggiungiActivity.this, response.results, idListener);
            rec_pasto.setAdapter(adapter);
        }

        @Override
        public void didError(String error) {
            Toast.makeText(RicercaEAggiungiActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };

    private final IngredientIdListener idListener = new IngredientIdListener() {
        @Override
        public void onClickedIngredient(String id) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RicercaEAggiungiActivity.this);
            alertDialog.setTitle("Insert Amount");
            final EditText input = new EditText(RicercaEAggiungiActivity.this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setRawInputType(Configuration.KEYBOARD_12KEY);
            alertDialog.setView(input);
            alertDialog.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if(input.getText().toString().equals("") || input.getText().toString().equals(null)) {
                        Toast.makeText(RicercaEAggiungiActivity.this, "Insert a valid amount", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        dialog.dismiss();
                        startActivity(new Intent(RicercaEAggiungiActivity.this, IngredientInfoActivity.class)
                                .putExtra("id", id)
                                .putExtra("amount", input.getText().toString()));
                    }
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    return;
                }
            });
            alertDialog.show();
        }
    };

}