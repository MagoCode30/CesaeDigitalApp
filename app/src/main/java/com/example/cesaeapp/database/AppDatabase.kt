package com.example.cesaeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cesaeapp.R
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.dao.CursoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

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
                )
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).cursoDao().insert(
                                    Curso(nome = "Análise de Dados",
                                        local = "Online",
                                        dataInicio = "2025-09-22",
                                        dataFim = "2026-01-27",
                                        preco = 150.00,
                                        duracao = "300 h",
                                        edicao = "Segunda",
                                        imagemResId = R.drawable.analisededados)
                                )
                                getDatabase(context).cursoDao().insert(
                                    Curso(nome = "Front-end Developer",
                                        local = "Porto",
                                        dataInicio = "2025-09-15",
                                        dataFim = "2026-05-19",
                                        preco = 400.00,
                                        duracao = "1050 h",
                                        edicao = "Primeira",
                                        imagemResId = R.drawable.frontend)
                                )
                                getDatabase(context).cursoDao().insert(
                                    Curso(nome = "AWS RE/START - Cloud Computing",
                                        local = "Braga",
                                        dataInicio = "2025-07-09",
                                        dataFim = "2026-03-30",
                                        preco = 400.00,
                                        duracao = "1200 h",
                                        edicao = "Terceira",
                                        imagemResId = R.drawable.aws)
                                )
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}