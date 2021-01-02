package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ActivityRepository(private val activityDao: ActivityDao) {

    val allActivity: Flow<List<BoredResponse>> = activityDao.getAllActivity()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(activity : BoredResponse) {
        activityDao.insert(activity)
    }
}