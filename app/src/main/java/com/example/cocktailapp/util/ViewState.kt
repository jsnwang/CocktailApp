package com.example.cocktailapp.util

import com.example.cocktailapp.model.resource.Category
import com.example.cocktailapp.model.resource.Details
import com.example.cocktailapp.model.resource.Drinks

sealed class ViewState {

    object Loading : ViewState() //loading state when the api has not given a response yet

    data class SuccessCategories(val category: Category) : ViewState() //success state when the api gives a successful response

    data class SuccessDrinks(val drinks: Drinks) : ViewState()

    data class SuccessDetails(val details: Details) : ViewState()

    data class Error(val error: String) : ViewState()
}