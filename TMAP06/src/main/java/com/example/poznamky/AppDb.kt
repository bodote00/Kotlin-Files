package com.example.poznamky

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: AppDb? = null
        fun getDatabase(context: Context) : AppDb {
            if (instance != null) {
                return instance as AppDb
            } else {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "MojeDatabase"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                return instance as AppDb
            }
        }
    }
}