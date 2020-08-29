package fergaral.tidesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey val id: Int,
    val portName: String,
    val portCode: String,
    val portLocation: Location,
    val portPhoto: String
)