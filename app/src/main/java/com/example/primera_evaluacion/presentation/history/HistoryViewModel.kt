package com.example.primera_evaluacion.presentation.history
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.data.local.AppDatabase
import com.example.primera_evaluacion.data.local.HistoryEntity
import com.example.primera_evaluacion.data.repository.HistoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: HistoryRepository

    val history: StateFlow<List<HistoryEntity>>

    init {

        val database =
            AppDatabase.getDatabase(application)

        repository =
            HistoryRepository(
                database.historyDao()
            )

        history =
            repository
                .getAll()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )
    }

    fun clearHistory() {

        viewModelScope.launch {

            repository.deleteAll()
        }
    }
}