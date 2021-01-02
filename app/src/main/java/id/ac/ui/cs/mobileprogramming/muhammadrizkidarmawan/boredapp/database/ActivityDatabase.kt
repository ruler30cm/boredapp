package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class BoredResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val activity: String? = null,
    val accessibility: Double? = null,
    val price: Double? = null,
    val link: String? = null,
    val type: String? = null,
    val key: String? = null,
    val participants: Int? = null
)