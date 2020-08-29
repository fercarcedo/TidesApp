package fergaral.tidesapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fergaral.tidesapp.db.entity.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>

    @Insert
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}