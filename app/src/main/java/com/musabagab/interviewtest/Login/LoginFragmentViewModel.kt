package com.musabagab.interviewtest.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.musabagab.interviewtest.databinding.FragmentLoginBinding

import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class LoginFragmentViewModelFactory(private val binding: FragmentLoginBinding) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginFragmentViewModel::class.java)) {
            return LoginFragmentViewModel(binding) as T
        }
        throw IllegalArgumentException("ViewModel is unknown")
    }
}

class LoginFragmentViewModel(private val binding: FragmentLoginBinding) : ViewModel() {

    private val _isFormValid = MutableLiveData<Boolean>()

    val isFormValid: LiveData<Boolean>
        get() = _isFormValid

    var username = ""
        set(value) {
            field = value
            validateForm()
        }

    var email = ""
        set(value) {
            field = value
            validateForm()
        }

    var password = ""
        set(value) {
            field = value
            validateForm()
        }

    private fun validateForm() {
        if (email.validator()
                .nonEmpty()
                .validEmail()
                .addSuccessCallback {
                    binding.emailField.error = null
                }.addErrorCallback {
                    binding.emailField.error = it
                }
                .check()
            &&
            username.validator()
                .nonEmpty()
                .startWithNonNumber()
                .noSpecialCharacters()
                .maxLength(8)
                .addSuccessCallback {
                    binding.userNameField.error = null
                }.addErrorCallback {
                    binding.userNameField.error = it
                }
                .check()

            &&
            password.validator()
                .nonEmpty()
                .noSpecialCharacters()
                .minLength(8)
                .maxLength(20)
                .addSuccessCallback {
                    binding.passwordField.error = null
                }.addErrorCallback {
                    binding.passwordField.error = it
                }
                .check()
        ) {
            _isFormValid.postValue(true)
        } else {
            _isFormValid.postValue(false)
        }

    }


}