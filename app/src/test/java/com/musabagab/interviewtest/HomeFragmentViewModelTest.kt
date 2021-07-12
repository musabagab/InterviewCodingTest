package com.musabagab.interviewtest

import com.musabagab.interviewtest.Home.HomeFragmentArgs
import com.musabagab.interviewtest.Home.HomeFragmentViewModel
import org.junit.Before
import org.junit.Test
import java.util.*

class HomeFragmentViewModelTest {

    lateinit var viewModel: HomeFragmentViewModel
    lateinit var args: HomeFragmentArgs

    @Before
    fun setUp() {
        args = HomeFragmentArgs(
            "musab",
            "email@gmail.com"
        )
        viewModel = HomeFragmentViewModel(
            args
        )
    }

    @Test
    fun itShouldContainTheUserNameInTheGreeting() {
        val greetingMessage = viewModel.getGreetingMessage()

        assert(greetingMessage.contains(args.username))
    }

    @Test
    fun itShouldReturnGoodEveningGreeting() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 16) // Evening

        val greetingMessage = viewModel.getGreetingMessage(calendar)
        assert(greetingMessage == "Good Evening, ${args.username}")
    }


}