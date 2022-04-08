@file:Suppress("DEPRECATION")

package hr.algebra.covidapp.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.preference.PreferenceManager
import android.view.View
import android.view.animation.AnimationUtils
import hr.algebra.covidapp.COUNTRIES_PROVIDER_CONTENT_URI
import hr.algebra.covidapp.COVID_PROVIDER_CONTENT_URI
import hr.algebra.covidapp.model.Country
import hr.algebra.covidapp.model.Item


fun View.applyAnimation(resourceId: Int)
        = startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun<reified T: Activity> Context.startActivity()
        = Intent(this, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(this)
}
inline fun<reified T: Activity> Context.startActivity(key: String,value: Int)
        = Intent(this, T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    putExtra(key,value)
    startActivity(this)
}

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast()
        = sendBroadcast(Intent(this, T::class.java))

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)

fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    if (network != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}

fun Context.fetchItems() : MutableList<Country>{
    val countries= mutableListOf<Country>()

    val cursor = contentResolver?.query(COUNTRIES_PROVIDER_CONTENT_URI, null, null, null, null, null)
if (cursor != null){
    while (cursor.moveToNext()){
        countries.add(Country(
                cursor.getLong(cursor.getColumnIndex(Country::_id.name)),
                cursor.getString(cursor.getColumnIndex(Country::iD.name)),
                cursor.getString(cursor.getColumnIndex(Country::country.name)),
                cursor.getString(cursor.getColumnIndex(Country::countryCode.name)),
                cursor.getString(cursor.getColumnIndex(Country::slug.name)),
                cursor.getInt(cursor.getColumnIndex(Country::newConfirmed.name)),
                cursor.getInt(cursor.getColumnIndex(Country::totalConfirmed.name)),
                cursor.getInt(cursor.getColumnIndex(Country::newDeaths.name)),
                cursor.getInt(cursor.getColumnIndex(Country::totalDeaths.name)),
                cursor.getInt(cursor.getColumnIndex(Country::newRecovered.name)),
                cursor.getInt(cursor.getColumnIndex(Country::totalRecovered.name)),
                cursor.getString(cursor.getColumnIndex(Country::date.name))
        ))
    }
}
    return countries
}


fun Context.fetchTotalItems() : Item{
    var totalItems:Item?=null



    val cursor = contentResolver?.query(COVID_PROVIDER_CONTENT_URI, null, null, null, null, null)

    if (cursor != null){
        while (cursor.moveToNext()){

            totalItems=Item(
                cursor.getLong(cursor.getColumnIndex(Item::_id.name)),
                cursor.getInt(cursor.getColumnIndex(Item::newConfirmed.name)),
                cursor.getInt(cursor.getColumnIndex(Item::totalConfirmed.name)),
                cursor.getInt(cursor.getColumnIndex(Item::newDeaths.name)),
                cursor.getInt(cursor.getColumnIndex(Item::totalDeaths.name)),
                cursor.getInt(cursor.getColumnIndex(Item::newRecovered.name)),
                cursor.getInt(cursor.getColumnIndex(Item::totalRecovered.name)),
                cursor.getString(cursor.getColumnIndex(Item::date.name))
            )

        }
    }
    return totalItems!!
}