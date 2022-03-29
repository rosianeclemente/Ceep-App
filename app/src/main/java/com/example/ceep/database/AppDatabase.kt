package com.example.ceep.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ceep.database.dao.NotaDao
import com.example.ceep.migrations.MIGRATIONS_1_2
import com.example.ceep.migrations.MIGRATION_2_3
import com.example.ceep.migrations.MIGRATION_3_4
import com.example.ceep.model.Nota

@Database(
    version = 4,
    entities = [Nota::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notaDao(): NotaDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "ceep.db"
            ).addMigrations(MIGRATIONS_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build()
        }
    }

}