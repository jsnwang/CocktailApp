package com.example.cocktailapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.databinding.ItemCategoryBinding
import com.example.cocktailapp.model.resource.Category
import com.example.cocktailapp.model.resource.Drink
import com.example.cocktailapp.model.resource.DrinkCategory
import com.example.cocktailapp.view.HomeFragment

class CategoryAdapter(
    private val category: Category,
    private val onCategoryClicked: (strCategory: String) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    //instantiates the viewbinding when the view is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = CategoryViewHolder.newInstance(parent, onCategoryClicked)

    //displays data at specified position
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(category.drinks[position])
    }

    //returns the total number of items in the recyclerview
    override fun getItemCount()
            = category.drinks.size

    //viewholder for recyclerview adapter
    class CategoryViewHolder (
        private val binding: ItemCategoryBinding,
        private val onCategoryClicked: (strCategory: String) -> Unit)
        : RecyclerView.ViewHolder(binding.root) {
        //func that runs for each item in the recyclerview
        fun bind(drink: DrinkCategory) = with(binding) {
            Log.d("text", drink.strCategory)
            tvCategory.text = drink.strCategory
            tvCategory.setOnClickListener {
                onCategoryClicked(drink.strCategory)
            }
        }

        //instantiates and inflates the viewbinding
        companion object{
            fun newInstance(parent: ViewGroup, onCategoryClicked: (strCategory: String) -> Unit) = ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
                .let { CategoryViewHolder(it, onCategoryClicked) }

        }
    }

}