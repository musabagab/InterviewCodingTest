package com.musabagab.interviewtest

import com.musabagab.interviewtest.Home.HomeFragmentArgs
import com.musabagab.interviewtest.Home.HomeFragmentViewModel
import org.junit.Before
import org.junit.Test

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
    fun testIfTheGreetingMessageContainTheName() {
        val greetingMessage = viewModel.getGreetingMessage()

        assert(greetingMessage.contains(args.username))
    }


}