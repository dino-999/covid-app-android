package hr.algebra.covidapp.api

import com.google.gson.annotations.SerializedName

class CovidItem (
        @SerializedName("ID") val iD : String,
        @SerializedName("Message") val message : String,
        @SerializedName("Global") val global : Global,
        @SerializedName("Countries") val countries : List<Countries>,
        @SerializedName("Date") val date : String
)

data class Global (

        @SerializedName("NewConfirmed") val newConfirmed : Int,
        @SerializedName("TotalConfirmed") val totalConfirmed : Int,
        @SerializedName("NewDeaths") val newDeaths : Int,
        @SerializedName("TotalDeaths") val totalDeaths : Int,
        @SerializedName("NewRecovered") val newRecovered : Int,
        @SerializedName("TotalRecovered") val totalRecovered : Int,
        @SerializedName("Date") val date : String
)


data class Countries (

        @SerializedName("ID") val iD : String,
        @SerializedName("Country") val country : String,
        @SerializedName("CountryCode") val countryCode : String,
        @SerializedName("Slug") val slug : String,
        @SerializedName("NewConfirmed") val newConfirmed : Int,
        @SerializedName("TotalConfirmed") val totalConfirmed : Int,
        @SerializedName("NewDeaths") val newDeaths : Int,
        @SerializedName("TotalDeaths") val totalDeaths : Int,
        @SerializedName("NewRecovered") val newRecovered : Int,
        @SerializedName("TotalRecovered") val totalRecovered : Int,
        @SerializedName("Date") val date : String

)

