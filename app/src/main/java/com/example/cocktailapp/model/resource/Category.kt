package com.example.cocktailapp.model.resource


import com.google.gson.annotations.SerializedName

data class Category(
    val drinks: List<DrinkCategory>
)