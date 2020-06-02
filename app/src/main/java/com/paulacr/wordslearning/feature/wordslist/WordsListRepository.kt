package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord
import io.reactivex.Completable

interface WordsListRepository {
    fun addTextWord(textWord: TextWord): Completable

    fun removeItem(word: TextWord)

    // todo
    fun moveToCategory()
}
