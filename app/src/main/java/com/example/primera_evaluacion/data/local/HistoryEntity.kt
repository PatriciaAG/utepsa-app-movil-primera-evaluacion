package com.example.primera_evaluacion.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val type: String,

    val title: String,

    val description: String,

    val date: Long = System.currentTimeMillis()
)