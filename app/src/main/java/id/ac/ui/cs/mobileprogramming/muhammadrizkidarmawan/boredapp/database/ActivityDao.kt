package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Query("SELECT * FROM activity")
    fun getAllActivity(): Flow<List<BoredResponse>>

    @Insert
    suspend fun insert(activity : BoredResponse)

    @Query("DELETE FROM activity")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteActivity(activity : BoredResponse)
}