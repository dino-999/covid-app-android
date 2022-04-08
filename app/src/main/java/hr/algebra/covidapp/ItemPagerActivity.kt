package hr.algebra.covidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import hr.algebra.covidapp.framework.fetchItems
import hr.algebra.covidapp.model.Country
import kotlinx.android.synthetic.main.activity_item_pager.*


const val ITEM_POSITION = "hr.algebra.covidapp.item_position"

class ItemPagerActivity : AppCompatActivity() {


    private lateinit var items: MutableList<Country>

    private var itemPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_pager)

        init()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        items = fetchItems()
        itemPosition= intent.getIntExtra(ITEM_POSITION, 0)
        viewPager.adapter=ItemPagerAdapter(items,this)
        viewPager.currentItem= itemPosition
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}