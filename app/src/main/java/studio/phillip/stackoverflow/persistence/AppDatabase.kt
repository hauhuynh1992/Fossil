package studio.phillip.stackoverflow.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import studio.phillip.stackoverflow.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        val DATABASE_NAME = "app_database"
    }

}