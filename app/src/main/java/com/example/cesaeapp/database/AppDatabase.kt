package com.example.cesaeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.dao.CursoDao

@Database(entities = [Curso::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cursoDao(): CursoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cursos_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}