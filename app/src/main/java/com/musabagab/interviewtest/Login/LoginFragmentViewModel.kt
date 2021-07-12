package com.musabagab.interviewtest.Login

import androidx.lifecycle.*
import com.musabagab.interviewtest.Database.UserEntity
import com.musabagab.interviewtest.Repository.UserDatabaseRepository
import com.musabagab.interviewtest.databinding.FragmentLoginBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.coroutines.launch
import java.util.*

class LoginFragmentViewModelFactory(
    private val binding: FragmentLoginBinding?,
    private val userRepo: UserDatabaseRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginFragmentViewModel::class.java)) {
            return LoginFragmentViewModel(binding, userRepo) as T
        }
        throw IllegalArgumentException("ViewModel is unknown")
    }
}

class LoginFragmentViewModel(
    private var binding: FragmentLoginBinding?,
    private val userRepo: UserDatabaseRepository?
) : ViewModel() {

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
                    binding?.emailField?.error = ""
                }.addErrorCallback {
                    binding?.emailField?.error = it
                }
                .check()
            &&
            username
                .validator()
                .nonEmpty()
                .startWithNonNumber()
                .noSpecialCharacters()
                .maxLength(8)
                .addSuccessCallback {
                    binding?.userNameField?.error = ""
                }.addErrorCallback {
                    binding?.userNameField?.error = it
                }
                .check()

            &&
            password
                .validator()
                .nonEmpty()
                .noSpecialCharacters()
                .minLength(8)
                .maxLength(20)
                .addSuccessCallback {
                    binding?.passwordField?.error = ""
                }.addErrorCallback {
                    binding?.passwordField?.error = it
                }
                .check()
        ) {// validation success
            _isFormValid.postValue(true)

            viewModelScope.launch {
                storeUserIntoInRoom()
            }

        } else {// validation failed
            _isFormValid.postValue(false)
        }
    }

    private suspend fun storeUserIntoInRoom() {
        userRepo?.insertUser(
            UserEntity(
                username = username,
                email = email,
                uid = UUID.randomUUID().toString()
            )
        )
    }


}