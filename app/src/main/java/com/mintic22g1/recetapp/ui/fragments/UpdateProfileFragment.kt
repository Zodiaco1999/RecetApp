package com.mintic22g1.recetapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.databinding.FragmentUpdateProfileBinding

class UpdateProfileFragment : Fragment() {
    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding: FragmentUpdateProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}