package com.assignment.geofinder

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.Collections.emptyList

/**
 * MainActivity serves as the primary entry point of the GeoFinder app.
 * It displays a list of countries and allows filtering by region using a Spinner.
 * Users can toggle between light and dark modes with the provided Switch at the top.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var countryListView: ListView
    private lateinit var countryList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views and variables
        countryListView = findViewById(R.id.countryListView)
        countryList = emptyList()
        val listView: ListView = findViewById(R.id.countryListView)

        // Get the continent list from resources
        val continentList = resources.getStringArray(R.array.continents_array)
        val spinner: Spinner = findViewById(R.id.regionSpinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, continentList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        // Check if data is available in the Singleton
        if (CountryDetailsSingleton.countryDetailsList.isEmpty()) {
            // Fetch data from the API if the Singleton list is empty
            fetchDataFromApi()
        } else {
            // If data is already present in the Singleton list, set up the Spinner listener to filter data
            // Update the ListView with the Singleton list
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, CountryDetailsSingleton.countryDetailsList.map { it.name })
            listView.adapter = adapter
        }

        var filteredList = emptyList<CountryDetails>()
        var flag: Boolean = false

        // Spinner item selection listener for filtering countries
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedContinent = spinner.getItemAtPosition(position) as String

                // Filter the list based on the selected continent
                filteredList = if (selectedContinent == "All") {
                    CountryDetailsSingleton.countryDetailsList // Show all countries if "All" is selected
                } else {
                    CountryDetailsSingleton.countryDetailsList.filter { it.region == selectedContinent }
                }

                flag = true
                // Update the ListView with the filtered list
                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, filteredList.map { it.name })
                listView.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected
            }
        }

        // ListView item click listener to open Country Details Activity
        countryListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            var selectedCountry = if (flag) {
                filteredList[position].name
            } else {
                countryList[position]
            }
            openCountryDetails(selectedCountry)
        }

        // Switch to toggle between dark and light modes
        val switch: Switch = findViewById(R.id.switchTheme)
        switch.setOnCheckedChangeListener { _, isChecked ->
            isDarkMode = isChecked
            toggleTheme()
        }
    }


    // Show loading indicator before making the API call
    fun showLoading() {
        // For example, if you have a ProgressBar with id "progressBar" in your layout:
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
    }

    // Hide loading indicator after fetching data
    fun hideLoading() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE
    }

    // Show error message if API call fails
    fun showError(message: String) {
        // For example, if you have a TextView with id "errorTextView" in your layout:
        val errorTextView: TextView = findViewById(R.id.errorTextView)
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
    }

    // Variable to track the current mode
    private var isDarkMode = true

    // Function to toggle between dark and light modes
    private fun toggleTheme() {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    // Function to fetch data from the API
    private fun fetchDataFromApi() {
        showLoading()
        val retrofitService = RetrofitInstance.getRetrofitInstance().create<ApiService>()
        val call = retrofitService.getAllCountries("hBk0zZDX4IVH7dMVX71svGhS7NCcRCY8DntGqWh1")

        call.enqueue(object : Callback<Map<String, CountryDetails>> {
            override fun onResponse(
                call: Call<Map<String, CountryDetails>>,
                response: Response<Map<String, CountryDetails>>
            ) {
                hideLoading()
                if (response.isSuccessful) {
                    val countryDetailsMap = response.body()
                    countryList = countryDetailsMap?.values?.map { it.name } ?: emptyList()

                    countryDetailsMap?.values?.let {
                        CountryDetailsSingleton.countryDetailsList.addAll(it)
                    }

                    val adapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        countryList
                    )
                    countryListView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Map<String, CountryDetails>>, t: Throwable) {
                hideLoading()
                showError("Network error")
            }
        })
    }

    // Function to open Country Details Activity
    private fun openCountryDetails(selectedCountry: String) {
        val intent = Intent(this, CountryDetailsActivity::class.java)
        intent.putExtra("selected_country", selectedCountry)
        startActivity(intent)
    }
}
