package com.musabagab.interviewtest.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.musabagab.interviewtest.Adapter.MedicineListAdapter
import com.musabagab.interviewtest.Repository.MedicineRepository

import com.musabagab.interviewtest.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val args: HomeFragmentArgs by navArgs()
    private val repo = MedicineRepository()
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
        viewModelFactory = HomeFragmentViewModelFactory(args)

        binding.greetingText.text = viewModel.getGreetingMessage()
        binding.userEmailText.text = viewModel.getUserEmail()

        // prepare the recyclerview
        binding.medicinesRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        val adapter =
            MedicineListAdapter { clickedMedicine ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    clickedMedicine
                )

                findNavController().navigate(action)
            }

        binding.medicinesRecyclerview.adapter = adapter

        // create the observer
        val listObserver = Observer<HomeFragmentViewState> { viewState ->
            if (viewState.medicines[0].name.isNotBlank()) {
                binding.progressBar.visibility = View.INVISIBLE
                binding.medicinesRecyclerview.visibility = View.VISIBLE
                adapter.submitList(viewState.medicines)
            }
        }
        // start observing
        repo.viewState.observe(viewLifecycleOwner, listObserver)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}



