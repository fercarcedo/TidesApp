package fergaral.tidesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fergaral.tidesapp.db.dao.FavoriteDao
import fergaral.tidesapp.db.entity.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}