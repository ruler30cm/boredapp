package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ActivityApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ActivityRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { ActivityRepository(database.activityDao())}
}