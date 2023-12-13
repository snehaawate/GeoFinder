package com.assignment.geofinder

/**
 * Singleton object to store a list of [CountryDetails].
 * This singleton holds a mutable list of [CountryDetails] to be accessed globally.
 */
object CountryDetailsSingleton {
    val countryDetailsList: MutableList<CountryDetails> = mutableListOf()
}
