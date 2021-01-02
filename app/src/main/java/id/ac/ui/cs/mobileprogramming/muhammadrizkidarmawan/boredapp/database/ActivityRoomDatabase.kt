package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(BoredResponse::class), version = 1, exportSchema = false)
public abstract class ActivityRoomDatabase : RoomDatabase() {
    abstract fun activityDao() : ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: ActivityRoomDatabase? = null

        fun getDatabase(context : Context, scope: CoroutineScope) : ActivityRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActivityRoomDatabase::class.java,
                    "activity_database"
                ).addCallback(ActivityDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class ActivityDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.activityDao())
                }
            }
        }
        suspend fun populateDatabase(activityDao: ActivityDao) {
            activityDao.deleteAll()

            var activity = BoredResponse(1, "Eat")
            activityDao.insert(activity)
        }
    }
}