package com.assignment.geofinder

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * CountryDetailsActivity displays detailed information about a selected country.
 * It shows the name, region, capital, and population of the chosen country.
 */
class CountryDetailsActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var officialNameTextView: TextView
    private lateinit var regionTextView: TextView
    private lateinit var capitalTextView: TextView
    private lateinit var populationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        // Initialize TextViews
        nameTextView = findViewById(R.id.nameTextView)
        regionTextView = findViewById(R.id.regionTextView)
        capitalTextView = findViewById(R.id.capitalTextView)
        populationTextView = findViewById(R.id.populationTextView)

        // Get the selected country name from the intent
        val selectedCountry = intent.getStringExtra("selected_country")

        // Fetch details for the selected country
        selectedCountry?.let {
            fetchCountryDetails(it)
        }
    }

    // Function to fetch and display country details
    private fun fetchCountryDetails(countryName: String) {
        // Find the country details from the Singleton list based on the country name
        val countryDetails = CountryDetailsSingleton.countryDetailsList.find { it.name == countryName }

        countryDetails?.let {
            // Display country details in TextViews
            nameTextView.text = it.name
            regionTextView.text = it.region
            capitalTextView.text = it.capital
            populationTextView.text = it.population.toString()
        }
    }
}
