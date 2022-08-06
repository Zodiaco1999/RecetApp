package com.mintic22g1.recetapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.data.viewmodels.LoginViewModel
import com.mintic22g1.recetapp.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.signupToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.signupButton.setOnClickListener {
            val id = binding.signupRadioGroup.checkedRadioButtonId
            val radioButton = requireActivity().findViewById(id) as RadioButton
            loginViewModel.signUp(
                binding.singUpEmail.text.toString(),
                binding.singUpPassword.text.toString(),
                binding.singUpName.text.toString(),
                radioButton.text.toString()
            )
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        loginViewModel.signUp.observe(viewLifecycleOwner, Observer {
            Snackbar.make(binding.root, "Registro exitoso", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        })
        loginViewModel.error.observe(viewLifecycleOwner, Observer { m ->
            Snackbar.make(binding.root, m, Snackbar.LENGTH_LONG).show()
        })
    }

}