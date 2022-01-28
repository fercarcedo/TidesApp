package fergaral.tidesapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fergaral.tidesapp.model.Location

@Entity
data class Favorite(
    @PrimaryKey val id: Int,
    val portName: String,
    val portCode: String,
    val portLat: Long,
    val portLng: Long,
    val portPhoto: String
)