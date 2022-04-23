package com.example.fact_e.Views.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fact_e.databinding.FragmentVentas3pmBinding


class Ventas3pm : Fragment() {

    private var _binding: FragmentVentas3pmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVentas3pmBinding.inflate(inflater, container, false)
        return binding.root
    }

}