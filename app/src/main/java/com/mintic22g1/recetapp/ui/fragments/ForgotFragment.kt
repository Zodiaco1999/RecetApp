package com.mintic22g1.recetapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.databinding.FragmentForgotBinding

class ForgotFragment : Fragment() {

    private var _binding: FragmentForgotBinding? = null
    private val binding: FragmentForgotBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onStart() {
        super.onStart()
        binding.forgotButton.setOnClickListener{
            val email: String = binding.forgotEmail.text.toString().trim { it <= ' '}
            if (email.isEmpty()) {
                Snackbar.make(binding.root, "Por favor ingrese el correo electrónico", Snackbar.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Snackbar.make(binding.root, "Se le ha enviado un correo de recuperación", Snackbar.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_forgotFragment_to_loginFragment)
                        } else {
                            Snackbar.make(binding.root, task.exception!!.message.toString(), Snackbar.LENGTH_LONG).show()
                        }
                    }
            }
        }

        binding.forgotToSignup.setOnClickListener {
            findNavController().navigate(R.id.action_forgotFragment_to_signUpFragment)
        }
    }
}