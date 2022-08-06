package com.mintic22g1.recetapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mintic22g1.recetapp.data.viewmodels.HomeViewModel
import com.mintic22g1.recetapp.databinding.FragmentRecetapDetailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.roundToInt

class RecetapDetailFragment : Fragment() {

    private var _binding: FragmentRecetapDetailBinding? = null
    private val binding:FragmentRecetapDetailBinding get() = _binding!!

    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecetapDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observerViewModels()
    }

    private fun observerViewModels(){
        homeViewModel.selected.observe(viewLifecycleOwner, Observer {
            binding.recetapDetailFragmentImage.setImageResource(it.image)
            binding.recetapDetailFragmentName.text = it.name
            binding.recetapDetailFragmentCategory.text = it.category
            binding.recetapDetailFragmentRating.rating = it.star.toFloat()
        })
        binding.recetapDetailFragmentDescription.text = "American diner right at the heart of Dubai, we were super hungry and craved for something with generous portions and we got just that."
        binding.recetapDetailFragmentRatingComent.rating = (Math.random()*5).toFloat()
        binding.recetapDetailFragmentComentDays.text = (Math.random()*15).roundToInt().toString()+" Days Ago"
        binding.recetapDetailFragmentComent.text = "The staff was very outstanding in all aspects, very professional, helpful and was making sure you get the best choice as he was recommending various options"

    }
}