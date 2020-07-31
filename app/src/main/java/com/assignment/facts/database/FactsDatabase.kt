package com.assignment.facts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assignment.facts.BuildConfig
import com.assignment.facts.database.dao.FactsDao
import com.assignment.facts.model.Facts

@Database(entities = [Facts::class], version = 1)
abstract class FactsDatabase : RoomDatabase() {

    abstract fun provideFactsDao(): FactsDao

    companion object {
        @Volatile
        private var INSTANCE: FactsDatabase? = null

        fun getDatabase(context: Context): FactsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, FactsDatabase::class.java, BuildConfig.DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}