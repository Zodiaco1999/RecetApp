package com.mintic22g1.recetapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.data.viewmodels.LoginViewModel
import com.mintic22g1.recetapp.databinding.FragmentLoginBinding
import com.mintic22g1.recetapp.isValidEmail
import com.mintic22g1.recetapp.isValidPassword
import com.mintic22g1.recetapp.ui.activities.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.loginToSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginForgotButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        binding.loginButton.setOnClickListener {
            if(!binding.loginEmail.text.toString().isValidEmail()){
                binding.loginEmailLayout.error = "Correo electronico invalido"
            }else {
                binding.loginEmailLayout.error = null
            }

            if(!binding.loginPassword.text.toString().isValidPassword()) {
                binding.loginPasswordLayout.error = "Minimo 8 caracteres"
            }else {
                binding.loginPasswordLayout.error = null
            }

            if(binding.loginEmail.text.toString().isValidEmail() && binding.loginPassword.text.toString().isValidPassword()){
                loginViewModel.login(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())
            }
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        loginViewModel.login.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_loginFragment_to_screenStartFragment)
//            val intent = Intent(context, HomeActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
        })
        loginViewModel.error.observe(viewLifecycleOwner, Observer { m ->
            Snackbar.make(binding.root, m, Snackbar.LENGTH_LONG).show()
        })
    }

}