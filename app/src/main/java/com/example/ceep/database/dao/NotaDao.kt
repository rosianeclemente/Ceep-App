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

    @Query("SELECT * FROM Nota WHERE desativada = 0")
    fun buscaTodas() : Flow<List<Nota>>

    @Query("SELECT * FROM Nota WHERE id = :id AND desativada = 0")
    fun buscaPorId(id: String): Flow<Nota>

    @Query("DELETE FROM Nota WHERE id = :id")
    suspend fun remove(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaLista(note: List<Nota>)

    @Query("SELECT * FROM Nota WHERE sincronizada = 0 AND desativada = 0")
    //
    fun buscaNaoSincronizada(): Flow<List<Nota>>

    @Query("UPDATE Nota SET desativada = 1 WHERE id = :id")
    suspend fun desativa(id: String)

    @Query("SELECT * FROM Nota WHERE desativada = 1")
    fun buscaDesativadas(): Flow<List<Nota>>
}