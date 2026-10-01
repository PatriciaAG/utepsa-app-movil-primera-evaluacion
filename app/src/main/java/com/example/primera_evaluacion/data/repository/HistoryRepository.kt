package com.example.primera_evaluacion.data.repository

import com.example.primera_evaluacion.data.local.HistoryDao
import com.example.primera_evaluacion.data.local.HistoryEntity
import kotlinx.coroutines.flow.Flow

class HistoryRepository(
    private val dao: HistoryDao
) {

    fun getAll(): Flow<List<HistoryEntity>> {
        return dao.getAll()
    }

    suspend fun insert(history: HistoryEntity) {
        dao.insert(history)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}