package com.mintic22g1.recetapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintic22g1.recetapp.ui.adapters.CardAdapter
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.ui.adapters.ServiceAdapter
import com.mintic22g1.recetapp.data.models.ServiceItemModel
import com.mintic22g1.recetapp.data.viewmodels.HomeViewModel
import com.mintic22g1.recetapp.databinding.FragmentHomeBinding
import com.mintic22g1.recetapp.interfaces.OnServiceClickListener
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var cardAdapter: CardAdapter
//    private lateinit var serviceAdapter: ServiceAdapter
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.services()

        val list = listOf(
            ServiceItemModel(id = "1", image = R.drawable.pizza),
            ServiceItemModel(id = "2", image = R.drawable.brochetas),
            ServiceItemModel(id = "3", image = R.drawable.hamburguesa),
            ServiceItemModel(id = "4", image = R.drawable.sushi),
            ServiceItemModel(id = "5", image = R.drawable.tacos),
            ServiceItemModel(id = "6", image = R.drawable.vegetales)
        )

        cardAdapter = CardAdapter(listOf())
        cardAdapter.listener = object : OnServiceClickListener {
            override fun onClick(item: ServiceItemModel) {
                cardAdapter.selected(item)

//                findNavController().navigate(R.id.action_homeFragment_to_recetapDetailFragment)

            }
        }

        val serviceAdapter = ServiceAdapter(list)
        serviceAdapter.listener = object : OnServiceClickListener {
            override fun onClick(item: ServiceItemModel) {
                serviceAdapter.selected(item)

//                findNavController().navigate(R.id.action_homeFragment_to_recetapDetailFragment)
            }
        }

        binding.homeFragmentRecyclerCard.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.homeFragmentRecyclerCard.itemAnimator = SlideInLeftAnimator()

        binding.homeFragmentRecycler.apply {
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.homeFragmentRecycler.itemAnimator = SlideInLeftAnimator()

        observeViewModels()
    }

    private fun observeViewModels() {
        homeViewModel.service.observe(viewLifecycleOwner, Observer {
            cardAdapter.changeDataSet(it)
        })
    }
}