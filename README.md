# GeoFinder
Data fetch from API and present it in a List

# Introduction
The project is about developing a Kotlin based android mobile application that will solve some knowledge-based problems in the society. With the help of publicly available API as data source, the project produces an app that can assist its users about gaining crucial knowledge about various countries around the world. The app interface also has important features that allows users to toggle between view modes. The app has been named as “Geo Finder”, as it helps the users find out more about the Geological boundaries of the world. More about this app is going to be discussed in the sections below.

# Functional Features
	
The main features of the application are as follows:
•Fetch details about all countries from a public API source
•Display a list of all country names on the app home screen
•Upon clicking on a country name, the user can view certain details about the selected country including their name, continent or region, capital city and population.
•There is to be an option for users to filter the list of countries that they want to be displayed. A filter based on the Continent or Region is going to be crucial.
•The app must have two visual modes that is a Dark mode and a Light Mode. There ought to be a switch that will allow the users to change the colour mode of the application, across all views or screens.
•The app is needed to have a local storage system or caching mechanism that will allow it to store the list of countries and not load it everytime from the API. Only when the app is launched, the data shall be fetched from the online API source, but after that, there must not be any need for internet connection. This means that the users can keep using the app, navigate between multiple screens and still use the app, offline.
•The app must have a loading screen in order to keep the users in-app, while the data is fetched from the online API, whenever the app is launched. However, once loaded, the loading state should not appear.

# The Chosen API

The countryapi.io has been chosen for this project. They provide a public endpoint to their dataset about every countries in the world, consisting of all necessary details.

# Development Approach

The app has been designed and developed using the Android Studio IDE and the Kotlin programming language. XML has been used to design and create the layouts of the application. Retrofit2 has been used to import and manage the online API services within the application. Furthermore, the ApiService Interface is created to make the code more reusable through inheritance features. Moreover, the project makes use of the Singleton design approach in order to promote code reusability, as objects and lists are created once and used all throughout the application screens. The CountryDetails model class is created to compose the Country information and help create individual instances for them, which are further stored into a list from the singleton class. 

# Testing Screenshots
 
Figure 1 Data Loading state (On launch)


 
Figure 2 Dark Mode (Home Screen)

 
Figure 3 Light Mode (Home Screen)

 
Figure 4Filtering by Region

 
Figure 5 Clicking on Italy for details

 
Figure 6 All Details about Italy

