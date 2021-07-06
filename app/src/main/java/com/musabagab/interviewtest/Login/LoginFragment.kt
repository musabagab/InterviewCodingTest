package com.musabagab.interviewtest.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.musabagab.interviewtest.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var viewModelFactory: LoginFragmentViewModelFactory
    private val viewModel: LoginFragmentViewModel by viewModels(
        factoryProducer = {
            viewModelFactory
        }
    )

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.emailField.editText?.doAfterTextChanged { email ->
            viewModel.email = email?.toString() ?: ""
        }

        binding.userNameField.editText?.doAfterTextChanged { username ->
            viewModel.username = username?.toString() ?: ""
        }

        binding.passwordField.editText?.doAfterTextChanged { password ->
            viewModel.password = password?.toString() ?: ""
        }

        context?.let {
            viewModelFactory = LoginFragmentViewModelFactory(binding)
        }

        viewModel.isFormValid.observe(viewLifecycleOwner, { valid ->
            binding.loginBtn.isEnabled = valid ?: false
        })

        binding.loginBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}