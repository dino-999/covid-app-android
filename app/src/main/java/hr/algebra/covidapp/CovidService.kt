package hr.algebra.covidapp

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.covidapp.api.CovidFetcher
import hr.algebra.covidapp.framework.sendBroadcast

private const val JOB_ID=1

class CovidService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
       CovidFetcher(this).fetchItems()
    }

    companion object{
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context,CovidService::class.java,JOB_ID,intent)
        }
    }

}