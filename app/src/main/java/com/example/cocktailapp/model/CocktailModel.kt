package com.example.cocktailapp.model

import android.util.Log
import com.example.cocktailapp.model.remote.CocktailService
import com.example.cocktailapp.model.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CocktailModel {
    //creates and holds the retrofit instance
    private val cocktailService by lazy { RetrofitInstance().cocktailService}

    /*
    gets list of movies
    String parameter to search for in the api query
    Using dispatchers IO thread coroutine
     */
    suspend fun getCategories() = withContext(Dispatchers.IO ){
        cocktailService.getCategories()
    }

    suspend fun getDrinks(drink: String) = withContext(Dispatchers.IO ){
        cocktailService.getDrinks(drink)
    }

    suspend fun getDetails(name: String) = withContext(Dispatchers.IO ){
        //Log.d("getDetails", cocktailService.getDetails(name).toString())
        cocktailService.getDetails(name)
    }
}