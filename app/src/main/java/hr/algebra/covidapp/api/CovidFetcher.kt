package hr.algebra.covidapp.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.covidapp.COUNTRIES_PROVIDER_CONTENT_URI
import hr.algebra.covidapp.COVID_PROVIDER_CONTENT_URI
import hr.algebra.covidapp.CovidReceiver
import hr.algebra.covidapp.framework.sendBroadcast
import hr.algebra.covidapp.model.Country
import hr.algebra.covidapp.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CovidFetcher(private val context: Context) {
    private var covidApi:CovidApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        covidApi = retrofit.create(CovidApi::class.java)
    }

    fun fetchItems() {
        val request = covidApi.fetchItems()

        request.enqueue(object: Callback<CovidItem> {
            override fun onResponse(
                call: Call<CovidItem>,
                response: Response<CovidItem>
            ) {
                if (response.body() != null) {
                    populateItems(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CovidItem>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun populateItems(covidItems:CovidItem) {

        GlobalScope.launch {

            covidItems.countries.forEach {
                val countryValues=ContentValues().apply {
                    put(Country::iD.name,it.iD)
                    put(Country::country.name,it.country)
                    put(Country::countryCode.name,it.countryCode)
                    put(Country::slug.name,it.slug)
                    put(Country::newConfirmed.name,it.newConfirmed)
                    put(Country::totalConfirmed.name,it.totalConfirmed)
                    put(Country::newDeaths.name,it.newDeaths)
                    put(Country::totalDeaths.name,it.totalDeaths)
                    put(Country::newRecovered.name,it.newRecovered)
                    put(Country::totalRecovered.name,it.totalRecovered)
                    put(Country::date.name,it.date)
                }
                context.contentResolver.insert(COUNTRIES_PROVIDER_CONTENT_URI,countryValues)
            }

            val values = ContentValues().apply {
                put(Item::newConfirmed.name,covidItems.global.newConfirmed)
                put(Item::totalConfirmed.name,covidItems.global.totalConfirmed)
                put(Item::newDeaths.name,covidItems.global.newDeaths)
                put(Item::totalDeaths.name,covidItems.global.totalDeaths)
                put(Item::newRecovered.name,covidItems.global.newRecovered)
                put(Item::totalRecovered.name,covidItems.global.totalRecovered)
                put(Item::date.name,covidItems.global.date)

            }
            context.contentResolver.insert(COVID_PROVIDER_CONTENT_URI,values)


            context.sendBroadcast<CovidReceiver>()
        }

    }
}