package com.example.karori.Listeners;

import com.example.karori.Models.SearchIngredientsResponse;

public interface SearchIngredientsListener {
    void didFetch(SearchIngredientsResponse response, String message);
    void didError(String error);
}
