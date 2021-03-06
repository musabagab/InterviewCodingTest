package com.musabagab.interviewtest.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

class HomeFragmentViewModelFactory(
    private val args: HomeFragmentArgs
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(args) as T
        }
        throw IllegalArgumentException("ViewModel is unknown")
    }
}

class HomeFragmentViewModel(
    private val args: HomeFragmentArgs
) : ViewModel() {


    fun getGreetingMessage(calendar: Calendar = Calendar.getInstance()): String {
        val greetingTime = when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            in 21..23 -> "Good Night"
            else -> "Hello"
        }

        return "$greetingTime, ${args.username}"
    }

    fun getUserEmail(): String = args.email


}