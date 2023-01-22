package com.example.karori.Listeners;

import com.example.karori.Models.IngredientInfoResponse;

public interface IngredientInfoListener {
    void didFetch(IngredientInfoResponse response, String message);
    void didError(String message);
}
