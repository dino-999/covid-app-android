package hr.algebra.covidapp.model


data class Country (
        var _id:Long?,
        val iD : String,
        val country : String,
        val countryCode : String,
        val slug : String,
        val newConfirmed : Int,
        val totalConfirmed : Int,
        val newDeaths : Int,
        val totalDeaths : Int,
        val newRecovered : Int,
        val totalRecovered : Int,
        val date : String
        )


