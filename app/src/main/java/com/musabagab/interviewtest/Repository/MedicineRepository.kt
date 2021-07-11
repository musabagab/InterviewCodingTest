package com.musabagab.interviewtest.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beust.klaxon.Klaxon
import com.beust.klaxon.PathMatcher
import com.musabagab.interviewtest.Api.createMedicineService
import com.musabagab.interviewtest.Database.Medicine
import com.musabagab.interviewtest.Home.HomeFragmentViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.StringReader
import java.util.regex.Pattern

class MedicineRepository {

    init {
        getMedicines()
    }

    private val _viewState: MutableLiveData<HomeFragmentViewState> = MutableLiveData()
    val viewState: LiveData<HomeFragmentViewState> = _viewState

    private var counter = 0


    private fun getMedicines() {
        createMedicineService().getData()?.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    // default value
                    _viewState.value = HomeFragmentViewState(
                        listOf(
                            Medicine("", "", ""),
                            Medicine("", "", ""),
                            Medicine("", "", ""),
                            Medicine("", "", "")
                        )
                    )


                    val pathMatcher = object : PathMatcher {
                        override fun pathMatches(path: String) = Pattern.matches(
                            ".*problems.*Diabetes.*medications.*medicationsClasses.*className.*associatedDrug.*",
                            path
                        )

                        override fun onMatch(path: String, value: Any) {

                            when {
                                path.contains("name") -> {
                                    val previousValue = _viewState.value
                                    previousValue?.medicines?.get(getIndex())
                                        ?.name = value.toString()

                                    _viewState.value = previousValue
                                }
                                path.contains("dose") -> {
                                    val previousValue = _viewState.value
                                    previousValue?.medicines?.get(getIndex())
                                        ?.dose = value.toString()
                                    _viewState.value = previousValue
                                }
                                else -> {// strength
                                    val previousValue = _viewState.value
                                    previousValue?.medicines?.get(getIndex())
                                        ?.strength = value.toString()
                                    _viewState.value = previousValue
                                }
                            }

                            counter++;

                        }
                    }

                    Klaxon()
                        .pathMatcher(pathMatcher)
                        .parseJsonObject(
                            StringReader(
                                Klaxon().toJsonString(response.body())
                            )
                        )
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e(
                    MedicineRepository::class.java.simpleName,
                    "Error loading the medicines from api, ${t.message}"
                )
            }

        })

    }

    private fun getIndex() =
        when (this.counter) {
            in 0..2 -> {
                0
            }
            in 3..5 -> {
                1
            }
            in 6..8 -> {
                2
            }
            in 9..11 -> {
                3
            }
            else -> {
                -1
            }
        }


}