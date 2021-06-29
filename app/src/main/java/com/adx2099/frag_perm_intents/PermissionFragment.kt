package com.adx2099.frag_perm_intents

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.adx2099.frag_perm_intents.databinding.FragmentPermissionBinding


class PermissionFragment : Fragment() {
    private var _binding: FragmentPermissionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPermissionBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnLocation.setOnClickListener {
            requestLocationPermission()
        }

        return view
    }

    private fun requestLocationPermission() {


    }

}