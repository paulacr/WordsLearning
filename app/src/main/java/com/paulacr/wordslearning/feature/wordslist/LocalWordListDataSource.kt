package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord
import io.reactivex.Completable

interface LocalWordListDataSource {
    fun addTextWord(word: TextWord): Completable

    fun removeItem(word: TextWord)

    // todo
    fun moveToCategory()
}
