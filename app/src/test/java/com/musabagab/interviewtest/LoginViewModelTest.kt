package com.musabagab.interviewtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.musabagab.interviewtest.Login.LoginFragmentViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var mockObserver: Observer<Boolean>

    lateinit var viewModel: LoginFragmentViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // for Coroutines
        Dispatchers.setMain(dispatcher)

        viewModel = LoginFragmentViewModel(
            null, null
        )
    }

    @Test
    fun testValidation() {
        viewModel.isFormValid.observeForever(mockObserver)

        viewModel.email = "musab@gmail.com"
        viewModel.username = "musab123"
        viewModel.password = "ilovekotlin"
        viewModel.password = "p@ssword"// special characters are not allowed

        verifyOrder {
            mockObserver.onChanged(false)
            mockObserver.onChanged(false)
            mockObserver.onChanged(true)
            mockObserver.onChanged(false)
        }
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}