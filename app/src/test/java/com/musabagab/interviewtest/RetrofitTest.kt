package com.musabagab.interviewtest

import com.musabagab.interviewtest.Api.createMedicineService
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import strikt.api.expectThat

class RetrofitTest {
    @Test
    fun `it should make a GET Request`() {
        val call: Call<Any>? = createMedicineService()
            .getData()

        expectThat(call?.request()) {
            assertThat("is GET method") {
                it?.method() == "GET"
            }
        }
    }

    @Test
    fun `it should have a 200 status code`() {
        createMedicineService()
            .getData()
            ?.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    expectThat(response.body()) {
                        assertThat("it has no empty body") {
                            it.toString().isNotEmpty()
                        }
                    }
                }
                override fun onFailure(call: Call<Any>, t: Throwable) {
                }

            })
    }
}