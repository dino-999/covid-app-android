package hr.algebra.covidapp.api

import retrofit2.Call
import retrofit2.http.GET

const val API_URL="https://api.covid19api.com/"
interface CovidApi {
    @GET("summary")
    fun fetchItems(): Call<CovidItem>
}