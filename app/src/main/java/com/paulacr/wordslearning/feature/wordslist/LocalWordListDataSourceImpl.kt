package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord
import com.paulacr.wordslearning.db.ImageWordDao
import com.paulacr.wordslearning.db.TextWordDao
import com.paulacr.wordslearning.di.wordListDataSourceModule
import io.reactivex.Single

class LocalWordListDataSourceImpl(
    private val textWordDao: TextWordDao,
    val imageWordDao: ImageWordDao
) : LocalWordListDataSource {
    override fun addTextWord(word: TextWord) = textWordDao.insert(word)

    override fun removeItem(word: TextWord) {
        TODO("Not yet implemented")
    }

    override fun moveToCategory() {
        TODO("Not yet implemented")
    }

    override fun getWordListItems(): Single<List<TextWord>> = textWordDao.loadAllUsers()
}
