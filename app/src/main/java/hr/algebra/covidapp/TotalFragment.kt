package hr.algebra.covidapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import hr.algebra.covidapp.framework.fetchTotalItems
import hr.algebra.covidapp.model.Item


class TotalFragment : Fragment() {

private lateinit var totalItems:Item


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        totalItems=requireContext().fetchTotalItems()
        return inflater.inflate(R.layout.fragment_total, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTotalConfirmed:TextView= view.findViewById(R.id.totalConfirmed)
        val tvTotalRecovered: TextView = view.findViewById(R.id.totalRecovered)
        val tvTotalDeaths: TextView = view.findViewById(R.id.totalDeaths)

        tvTotalConfirmed.text=totalItems.totalConfirmed.toString()
        tvTotalRecovered.text=totalItems.totalRecovered.toString()
        tvTotalDeaths.text=totalItems.totalDeaths.toString()


    }

}

