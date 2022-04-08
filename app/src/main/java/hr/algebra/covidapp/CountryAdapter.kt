package hr.algebra.covidapp

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.covidapp.framework.startActivity
import hr.algebra.covidapp.model.Country
import java.util.*
import kotlin.collections.ArrayList


class CountryAdapter(private val countries:MutableList<Country>, private val context: Context) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {





    class ViewHolder(countryView: View) : RecyclerView.ViewHolder(countryView) {
        private val ivCountry:ImageView= countryView.findViewById(R.id.ivCountry)
        private val tvCountry: TextView = countryView.findViewById(R.id.tvCountry)

        fun bind(country: Country){
            tvCountry.text= country.country
            ivCountry.setImageResource(R.drawable.countries)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val countryView=LayoutInflater.from(parent.context).inflate(
                R.layout.country,parent,false
        )
        return ViewHolder(countryView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.setOnClickListener{
            context.startActivity<ItemPagerActivity>(ITEM_POSITION,position)
        }
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int =countries.size





}