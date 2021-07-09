package com.musabagab.interviewtest.Details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsFragmentViewModelFactory(
    private val args: DetailsFragmentArgs
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsFragmentViewModel::class.java)) {
            return DetailsFragmentViewModel(args) as T
        }
        throw IllegalArgumentException("ViewModel is unknown")
    }
}

class DetailsFragmentViewModel(
    private val args: DetailsFragmentArgs
) : ViewModel() {

    fun getName() = args.medicine.name


    fun getDose(): String {

        if (args.medicine.dose.isEmpty()) {
            return "-"
        }
        return args.medicine.dose
    }

    fun getStrength() = args.medicine.strength

}