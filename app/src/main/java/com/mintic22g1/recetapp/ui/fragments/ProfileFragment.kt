package com.mintic22g1.recetapp.ui.fragments

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.mintic22g1.recetapp.databinding.FragmentProfileBinding
import com.mintic22g1.recetapp.ui.activities.LoginActivity
import com.mintic22g1.recetapp.utils.CAMERA_PERMISSION_CODE
import com.mintic22g1.recetapp.utils.REQUEST_IMAGE_CODE
import com.mintic22g1.recetapp.utils.checkPermission

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.profileFragmentLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.profileFragmentImage.setOnClickListener {
            if(this.checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)) {
                //TODO: open camera
                openCamera()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //TODO: open camera
            openCamera()
        } else {
            Snackbar.make(binding.root, "No se ha otorgado el permiso", Snackbar.LENGTH_LONG)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CODE) {
            val extras = data!!.extras!!
            val image = extras["data"] as Bitmap?
            binding.profileFragmentImage.setImageBitmap(image)
        }
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE)
        }catch (e: ActivityNotFoundException) {

        }
    }
}