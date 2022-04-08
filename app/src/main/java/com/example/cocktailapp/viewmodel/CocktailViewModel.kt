package com.example.cocktailapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.model.CocktailModel
import com.example.cocktailapp.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class CocktailViewModel : ViewModel() {

    private val _state =
        MutableStateFlow<ViewState>(ViewState.Loading) //mutable state flow that holds the ViewState
    val state: StateFlow<ViewState> get() = _state

    fun getCategories() {
        viewModelScope.launch { //launch coroutine
            val state =
                try {  //state is set to success or fail depending on if the trycatch block fails or not
                    val category = CocktailModel.getCategories()
                    ViewState.SuccessCategories(category) //sets the viewstate to success and passes the movies as a parameter
                } catch (ex: Exception) { //catches the try block if the getMovies call fails
                    ViewState.Error(
                        ex.message ?: "Catch Error"
                    ) //sets the state to error and passes the error message
                }
            _state.value =
                state //sets the mutable state flow value as the state determined by the trycatch block
        }
    }

    fun getDrinks(drink: String) {
        viewModelScope.launch { //launch coroutine
            val state =
                try {  //state is set to success or fail depending on if the trycatch block fails or not
                    val drinks = CocktailModel.getDrinks(drink)
                    ViewState.SuccessDrinks(drinks) //sets the viewstate to success and passes the movies as a parameter
                } catch (ex: Exception) { //catches the try block if the getMovies call fails
                    ViewState.Error(
                        ex.message ?: "Catch Error"
                    ) //sets the state to error and passes the error message
                }
            _state.value =
                state //sets the mutable state flow value as the state determined by the trycatch block
        }
    }

    fun getDetails(name: String) {
        viewModelScope.launch { //launch coroutine
            val state =
                try { //state is set to success or fail depending on if the trycatch block fails or not
                    Log.d("viewmodel name", name)
                    val details = CocktailModel.getDetails("Almond Joy")
                    ViewState.SuccessDetails(details) //sets the viewstate to success and passes the movies as a parameter
                } catch (ex: Exception) { //catches the try block if the getMovies call fails
                    ViewState.Error(
                        ex.message ?: "Catch Error"
                    ) //sets the state to error and passes the error message
                }
            _state.value =
                state //sets the mutable state flow value as the state determined by the trycatch block
        }
    }
}