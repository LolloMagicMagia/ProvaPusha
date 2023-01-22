package com.example.karori.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karori.Listeners.IngredientIdListener;
import com.example.karori.Models.Result;
import com.example.karori.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchedIngredientsAdapter extends RecyclerView.Adapter<SearchedIngredientsViewHolder>{
    Context context;
    List<Result> results;
    IngredientIdListener idListener;

    public SearchedIngredientsAdapter(Context context, List<Result> results, IngredientIdListener idListener) {
        this.context = context;
        this.results = results;
        this.idListener = idListener;
    }

    @NonNull
    @Override
    public SearchedIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchedIngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.alimenti, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedIngredientsViewHolder holder, int position) {
        String imageUrl = "https://spoonacular.com/cdn/ingredients_250x250/" + results.get(position).image;
        if (results.get(position).name == null) {
            holder.txt_title.setText("Daniela Micucci");
        } else {
            holder.txt_title.setText(results.get(position).name);
        }
        holder.txt_title.setSelected(true);
        Picasso.get().load(imageUrl).into(holder.aliment_image);

        holder.alimenti_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idListener.onClickedIngredient(String.valueOf(results.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}

class SearchedIngredientsViewHolder extends RecyclerView.ViewHolder {
    CardView alimenti_card;
    TextView txt_title;
    ImageView aliment_image;
    public SearchedIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        alimenti_card = itemView.findViewById(R.id.alimenti_card);
        txt_title = itemView.findViewById(R.id.testo_alimento);
        aliment_image = itemView.findViewById(R.id.aliment_image);
    }
}