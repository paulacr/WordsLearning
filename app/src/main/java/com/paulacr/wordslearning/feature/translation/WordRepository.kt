package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Word

interface WordRepository {

    suspend fun getRandomWord(): Word

    suspend fun addWord(word: Word): Long

    // todo clearAll

    // todo delete(word: Word)
}
