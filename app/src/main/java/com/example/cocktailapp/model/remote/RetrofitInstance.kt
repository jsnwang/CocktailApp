package com.example.cocktailapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/") //base url for the api call
        .addConverterFactory(GsonConverterFactory.create()) //converts the json data into a java class
        .build() //builds the retrofit instance

    val cocktailService: CocktailService by lazy {
        retrofit.create() //creates retrofit instance
    }
}