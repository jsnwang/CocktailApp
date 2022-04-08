package com.example.cocktailapp.model.remote

import android.util.Log
import com.example.cocktailapp.model.resource.Category
import com.example.cocktailapp.model.resource.Details
import com.example.cocktailapp.model.resource.Drinks
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    companion object{
        const val BASE_URL = "https://www.thecocktaildb.com/"
    }

    //suspend function to make the api call for movie searches
    //has to run in a coroutine
    @GET("api/json/v1/1/list.php") //GET request to retrieve data from the api
    suspend fun getCategories (
        @Query("c") list : String = "list"
    ) : Category

    @GET("api/json/v1/1/filter.php") //GET request to retrieve data from the api
    suspend fun getDrinks (
        @Query("c") drink : String
    ) : Drinks

    @GET("api/json/v1/1/search.php") //GET request to retrieve data from the api
    suspend fun getDetails (
        @Query("s") name : String
    ) : Details
}