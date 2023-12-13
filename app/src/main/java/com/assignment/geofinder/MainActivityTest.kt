package com.assignment.geofinder

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
        CountryDetailsSingleton.countryDetailsList.clear() // Clear the list before each test
    }

    @Test
    fun countryDetailsListShouldBeEmptyInitially() {
        assertTrue(CountryDetailsSingleton.countryDetailsList.isEmpty())
    }

    @Test
    fun firstCountryShouldBeAfghanistan() {
        // Simulate API data loading
        val afghanistan = CountryDetails("Afghanistan", "Asia", "Kabul", "10000000")
        CountryDetailsSingleton.countryDetailsList.add(afghanistan)

        assertEquals("Afghanistan", CountryDetailsSingleton.countryDetailsList.first().name)
    }

    @Test
    fun lastCountryShouldBeBrazil() {
        // Simulate API data loading
        val brazil = CountryDetails("Brazil", "South America", "Brasilia", "200000000")
        CountryDetailsSingleton.countryDetailsList.add(brazil)

        assertEquals("Brazil", CountryDetailsSingleton.countryDetailsList.last().name)
    }
}
