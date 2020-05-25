package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.common.Exceptions
import com.paulacr.wordslearning.data.Word

class WordsListRepositoryImpl : WordsListRepository {

    var wordsList = mutableListOf<Word>()

    override fun addItem(word: Word) {
        if (wordsList.contains(word)) throw Exceptions.WordAlreadySavedException()
        wordsList.add(word)
    }

    override fun removeItem(word: Word) {
        wordsList.remove(word)
    }

    override fun moveToCategory() {
        TODO("Not yet implemented")
    }
}