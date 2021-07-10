package com.musabagab.interviewtest.Details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.musabagab.interviewtest.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var viewModelFactory: DetailsFragmentViewModelFactory
    private val viewModel: DetailsFragmentViewModel by viewModels(
        factoryProducer = {
            viewModelFactory
        }
    )

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModelFactory = DetailsFragmentViewModelFactory(args)

        binding.detailsMedicineName.text = viewModel.getName()
        binding.detailsMedicineDose.text = viewModel.getDose()
        binding.detailsMedicineStrength.text = viewModel.getStrength()


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}