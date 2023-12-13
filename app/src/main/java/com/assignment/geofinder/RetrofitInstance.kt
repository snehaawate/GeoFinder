package com.assignment.geofinder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to provide Retrofit instance for API calls related to country details.
 */
object RetrofitInstance {
    private const val BASE_URL = "https://countryapi.io/api/"

    /**
     * Creates and provides a Retrofit instance.
     * @return Retrofit instance configured with the base URL and Gson converter factory.
     */
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}


