package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord

interface WordsListRepository {
    fun addItem(word: TextWord)

    fun removeItem(word: TextWord)

    // todo
    fun moveToCategory()
}
