package com.example.cocktailapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.databinding.ItemCategoryBinding
import com.example.cocktailapp.databinding.ItemDrinkBinding
import com.example.cocktailapp.model.resource.Category
import com.example.cocktailapp.model.resource.Drink
import com.example.cocktailapp.model.resource.DrinkCategory
import com.example.cocktailapp.model.resource.Drinks
import com.example.cocktailapp.view.HomeFragment

class DrinksAdapter(
    private val drinks: Drinks,
    private val onDrinkClicked: (strDrink: String) -> Unit) : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>(){

    //instantiates the viewbinding when the view is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DrinksViewHolder.newInstance(parent, onDrinkClicked)

    //displays data at specified position
    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.bind(drinks.drinks[position])
    }

    //returns the total number of items in the recyclerview
    override fun getItemCount()
            = drinks.drinks.size

    //viewholder for recyclerview adapter
    class DrinksViewHolder (
        private val binding: ItemDrinkBinding,
        private val onDrinkClicked: (strDrink: String) -> Unit)
        : RecyclerView.ViewHolder(binding.root) {
        //func that runs for each item in the recyclerview
        fun bind(drink: Drink) = with(binding) {
            Log.d("text", drink.strDrink)
            tvCategory.text = drink.strDrink
            tvCategory.setOnClickListener {
                onDrinkClicked(drink.strDrink)
            }
        }

        //instantiates and inflates the viewbinding
        companion object{
            fun newInstance(parent: ViewGroup, onDrinkClicked: (strDrink: String) -> Unit) = ItemDrinkBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
                .let { DrinksViewHolder(it, onDrinkClicked) }

        }
    }

}