package com.paulacr.wordslearning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_word")
data class TextWord(
    val name: String,
    val translation: String,
    override var type: WordItemType = WordItemType.TEXT_WORD_TYPE
) : WordItem() {

    @PrimaryKey(autoGenerate = true) var id: Int? = 0
}
