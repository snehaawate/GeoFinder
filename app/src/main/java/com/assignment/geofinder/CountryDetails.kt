package com.assignment.geofinder

/**
 * Represents details about a country.
 * This is a Model class
 * @property name The name of the country.
 * @property region The region or continent the country belongs to.
 * @property capital The capital city of the country.
 * @property population The population of the country.
 */
data class CountryDetails(
    val name: String,
    val region: String,
    val capital: String,
    val population: String
)
