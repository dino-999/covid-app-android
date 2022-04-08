package hr.algebra.covidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import hr.algebra.covidapp.framework.fetchItems
import hr.algebra.covidapp.model.Country
import kotlinx.android.synthetic.main.fragment_countries.*


class CountriesFragment : Fragment() {

    private lateinit var countries:MutableList<Country>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        countries=requireContext().fetchItems()
        return inflater.inflate(R.layout.fragment_countries, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val countryAdapter=CountryAdapter(countries,requireContext())
        rvCountries.apply {
            layoutManager =LinearLayoutManager(activity)
            adapter=countryAdapter
        }

    }

}
