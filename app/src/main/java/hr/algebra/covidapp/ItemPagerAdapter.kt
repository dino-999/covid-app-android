package hr.algebra.covidapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.covidapp.model.Country

class ItemPagerAdapter(private val items: MutableList<Country>, private val context: Context) :
    RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>() {


    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvCountryName: TextView = itemView.findViewById(R.id.tvCountryName)
        private val tvTotalConfirmed: TextView = itemView.findViewById(R.id.tvTotalConfirmed)
        private val tvTotalRecovered: TextView = itemView.findViewById(R.id.tvTotalRecovered)
        private val tvTotalDeaths: TextView = itemView.findViewById(R.id.tvTotalDeaths)

        fun bind(country: Country){
            tvCountryName.text=country.country
            tvTotalConfirmed.text= country.totalConfirmed.toString()
            tvTotalRecovered.text= country.totalRecovered.toString()
            tvTotalDeaths.text= country.totalDeaths.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_pager,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}