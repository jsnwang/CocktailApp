package com.example.cocktailapp.model.resource


import com.google.gson.annotations.SerializedName

data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)