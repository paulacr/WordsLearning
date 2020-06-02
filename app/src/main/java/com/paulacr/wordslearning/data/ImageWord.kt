package com.paulacr.wordslearning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_word")
data class ImageWord(
    @PrimaryKey val id: Int,
    val imageRes: Int,
    val name: String,
    val pronunciation: String?,
    override var type: WordItemType = WordItemType.IMAGE_WORD_TYPE
) : WordItem()
