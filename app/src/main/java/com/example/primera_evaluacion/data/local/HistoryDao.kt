package com.example.primera_evaluacion.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(history: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY date DESC")
    fun getAll(): Flow<List<HistoryEntity>>

    @Query("DELETE FROM history")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(history: HistoryEntity)
}