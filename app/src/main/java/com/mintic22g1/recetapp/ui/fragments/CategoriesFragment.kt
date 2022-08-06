package com.mintic22g1.recetapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.data.viewmodels.HomeViewModel
import com.mintic22g1.recetapp.databinding.FragmentCategoriesBinding
import com.mintic22g1.recetapp.interfaces.OnRecipeClickListener
import com.mintic22g1.recetapp.ui.adapters.RecipeAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesFragment : Fragment() {
//  private  val args: CategoriesFragmentArgs by NavArgs()
    private var _binding: FragmentCategoriesBinding? = null
    private val binding: FragmentCategoriesBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var categories: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        recipeAdapter = RecipeAdapter(listOf())
        homeViewModel.recipes(null)
        categories = listOf("Frutas", "Carnes", "Cerdo", "Vegetales")
        binding.categoriesFragmentSearch.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories))
        binding.categoriesFragmentSearch.setOnItemClickListener { parent, view, position, id ->
            homeViewModel.recipes(categories[position])
        }

        recipeAdapter._listener = object : OnRecipeClickListener {
            override fun onClick(item: RecipeItemModel) {
                homeViewModel.recipeSelected(item)
                findNavController().navigate(R.id.action_categoriesFragment_to_recetapDetailFragment)
            }
        }
        binding.categoriesFragmentRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        observeViewModels()
    }
    private fun observeViewModels() {
        homeViewModel.recipe.observe(viewLifecycleOwner, Observer {
            recipeAdapter.changeDataSet(it)
        })
    }

}