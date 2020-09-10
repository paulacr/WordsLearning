package com.paulacr.wordslearning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_word")
data class TextWord(
    var name: String,
    val translation: String,
    override var fromLanguage: Language,
    override var toLanguage: Language,
    override var type: WordItemType = WordItemType.TEXT_WORD_TYPE
) : WordItem() {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
