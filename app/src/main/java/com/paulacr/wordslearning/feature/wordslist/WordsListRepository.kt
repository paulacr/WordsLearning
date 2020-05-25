package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.Word

interface WordsListRepository {
    fun addItem(word: Word)

    fun removeItem(word: Word)

    // todo
    fun moveToCategory()
}