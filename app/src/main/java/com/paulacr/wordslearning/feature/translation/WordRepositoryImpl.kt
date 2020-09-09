package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Word
import com.paulacr.wordslearning.db.WordDao
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordDao: WordDao
) : WordRepository {
    override suspend fun getRandomWord(): Word {
        return wordDao.getAllWords().random()
    }

    override suspend fun addWord(word: Word): Long {
        return wordDao.saveWord(word)
    }
}
