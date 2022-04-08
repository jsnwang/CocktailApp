package com.example.cocktailapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cocktailapp.adapter.CategoryAdapter
import com.example.cocktailapp.databinding.FragmentHomeBinding
import com.example.cocktailapp.model.resource.Category
import com.example.cocktailapp.util.ViewState
import com.example.cocktailapp.viewmodel.CocktailViewModel
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding?= null //viewbinding of home fragment
    private val binding get() = _binding!! //gets the above value
    private val viewModel by viewModels<CocktailViewModel>() //get viewmodel using kotlin property delegate

    //is called when the view is first initialized
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        //inflate the viewbinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false) //infate the viewbinding

        initViews()
        initObservers()

        return binding.root //returns the root of the viewbinding which is the constraintlayout
    }

    private fun initViews() {
        viewModel.getCategories()
    }

    private fun initObservers() = with(viewModel){
        lifecycleScope.launchWhenStarted{ //launch lifecycle coroutine
            state.collectLatest { state -> //collect the stateflow data
                Log.d("state", state.toString())
                binding.loader.isVisible = state is ViewState.Loading //Makes loader visible if ViewState is Loading
                if (state is ViewState.SuccessCategories) handleSuccess(state.category)
                //when the api responds successfully set the ViewState to Success and pass the movies
                if (state is ViewState.Error) handleError(state.error)
                //if the api encounters an error set the ViewState to Error and pass the error message
            }
        }
    }

    private fun handleSuccess(category: Category) {
        binding.rvCategories.adapter =  CategoryAdapter(category, ::onCategoryClicked)
    }

    private fun handleError(error: String) {
        //shows the error in a toast
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun onCategoryClicked(category: String)
    {
        val bundle = bundleOf("category" to category)
        findNavController().navigate(com.example.cocktailapp.R.id.drinkFragment, bundle)

    }
}