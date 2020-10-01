package com.dicoding.capstoneproject.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.capstoneproject.core.data.local.entity.KamusEntity

@Database(entities = [KamusEntity::class], version = 1, exportSchema = false)
abstract class KamusDatabase : RoomDatabase() {

    abstract fun kamusDao(): KamusDao

    companion object {
        @Volatile
        private var INSTANCE: KamusDatabase? = null

        fun getInstance(context: Context): KamusDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KamusDatabase::class.java,
                    "Kamus.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}