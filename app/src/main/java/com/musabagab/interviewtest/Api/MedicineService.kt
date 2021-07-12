package com.musabagab.interviewtest.Api

import com.musabagab.interviewtest.BASE_URL
import com.musabagab.interviewtest.MEDICINES_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface MedicineService {
    // Request method and URL specified in the annotation
    @GET(MEDICINES_URL)
    fun getData(): Call<Any>?
}

fun createMedicineService(): MedicineService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(MedicineService::class.java)
}

