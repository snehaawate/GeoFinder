package com.assignment.geofinder

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * ApiService interface to define API endpoints for country-related operations.
 */
interface ApiService {
    /**
     * Fetches all countries.
     * @param apiKey API key for authentication.
     * @return Call object for fetching a map of country details.
     */
    @GET("all")
    fun getAllCountries(@Query("apikey") apiKey: String): Call<Map<String, CountryDetails>>

    /**
     * Fetches a country by name.
     * @param countryName Name of the country to fetch.
     * @param apiKey API key for authentication.
     * @return Call object for fetching details of a specific country.
     */
    @GET("name/{countryName}")
    fun getCountryByName(
        @Path("countryName") countryName: String,
        @Query("apikey") apiKey: String
    ): Call<Map<String, CountryDetails>>
}

