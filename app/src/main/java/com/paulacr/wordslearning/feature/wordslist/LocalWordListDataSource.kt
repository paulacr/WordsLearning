package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord
import io.reactivex.Completable
import io.reactivex.Single

interface LocalWordListDataSource {
    fun addTextWord(word: TextWord): Completable

    fun removeItem(word: TextWord)

    // todo
    fun moveToCategory()

    fun getWordListItems(): Single<List<TextWord>>
}
