package hr.algebra.covidapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.covidapp.framework.setBooleanPreference
import hr.algebra.covidapp.framework.startActivity

class CovidReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
      context.setBooleanPreference(DATA_IMPORTED,true)
      context.startActivity<HostActivity>()
    }
}