package com.d121201076.tudu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Tugas::class], version = 1)
abstract class TugasDatabase: RoomDatabase() {
    abstract fun Dao(): Dao

            companion object {
            @Volatile
            private var INSTANCE: TugasDatabase? = null

            fun getDatabase(context: Context): TugasDatabase {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TugasDatabase::class.java,
                        "tugas_database"
                    ).build()
                    INSTANCE = instance
                    return instance
            }
        }
    }
}
