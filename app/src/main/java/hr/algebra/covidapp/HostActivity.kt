package hr.algebra.covidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_host)


        val totalFragment=TotalFragment()
        val countriesFragment=CountriesFragment()
        val aboutFragment=AboutFragment()

        makeCurrentFragment(totalFragment)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuTotal->makeCurrentFragment(totalFragment)
                R.id.menuCountries-> makeCurrentFragment(countriesFragment)
                R.id.menuAbout-> makeCurrentFragment(aboutFragment)
            }
            true
        }


    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    }












}