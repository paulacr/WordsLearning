package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord
import com.paulacr.wordslearning.db.ImageWordDao
import com.paulacr.wordslearning.db.TextWordDao

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
}
