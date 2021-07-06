package com.musabagab.interviewtest.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.musabagab.interviewtest.R
import com.musabagab.interviewtest.databinding.FragmentHomeBinding
import com.musabagab.interviewtest.databinding.FragmentLoginBinding


class HomeFragment : Fragment() {


    private lateinit var viewModelFactory: HomeFragmentViewModelFactory
    private val viewModel: HomeFragmentViewModel by viewModels(
        factoryProducer = {
            viewModelFactory
        }
    )

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}