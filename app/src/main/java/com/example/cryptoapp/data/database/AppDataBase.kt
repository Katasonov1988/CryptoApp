package com.example.cryptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDBModel::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        @Volatile
        private var db: AppDataBase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                var instance = db
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    db = instance
                }
                return instance
            }
        }
    }

    abstract fun coinInfoDao(): CoinInfoDao

}


