package hr.algebra.covidapp.model

data class Item (
    var _id:Long?,
    val newConfirmed : Int,
    val totalConfirmed : Int,
    val newDeaths : Int,
    val totalDeaths : Int,
    val newRecovered : Int,
    val totalRecovered : Int,
    val date : String
)