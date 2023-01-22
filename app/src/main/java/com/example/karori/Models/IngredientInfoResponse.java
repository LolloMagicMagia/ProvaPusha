package com.example.karori.Models;

import java.util.ArrayList;

public class IngredientInfoResponse {
    public int id;
    public String original;
    public String originalName;
    public String name;
    public double amount;
    public String unit;
    public String unitShort;
    public String unitLong;
    public ArrayList<String> possibleUnits;
    public EstimatedCost estimatedCost;
    public String consistency;
    public ArrayList<String> shoppingListUnits;
    public String aisle;
    public String image;
    public ArrayList<Object> meta;
    public Nutrition nutrition;
    public ArrayList<String> categoryPath;
}
