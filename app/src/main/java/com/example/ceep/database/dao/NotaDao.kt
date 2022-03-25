package com.example.ceep.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ceep.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(note: Nota)

    @Query("SELECT * FROM Nota")
    fun buscaTodas() : Flow<List<Nota>>

    @Query("SELECT * FROM Nota WHERE id = :id")
    fun buscaPorId(id: Long): Flow<Nota>

    @Query("DELETE FROM Nota WHERE id = :id")
    suspend fun remove(id: Long)
}