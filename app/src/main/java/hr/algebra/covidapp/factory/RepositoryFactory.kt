package hr.algebra.covidapp.factory

import android.content.Context
import hr.algebra.covidapp.dao.CountriesSqlHelper

fun getCountriesRepository(context: Context?)=CountriesSqlHelper(context)