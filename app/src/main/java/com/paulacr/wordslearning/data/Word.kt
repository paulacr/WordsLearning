package com.paulacr.wordslearning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    var language: Language
)
