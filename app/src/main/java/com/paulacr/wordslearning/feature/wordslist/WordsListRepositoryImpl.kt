package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.common.Exceptions
import com.paulacr.wordslearning.data.TextWord

class WordsListRepositoryImpl : WordsListRepository {

    var wordsList = mutableListOf<TextWord>()

    override fun addItem(word: TextWord) {
        if (wordsList.contains(word)) throw Exceptions.WordAlreadySavedException()
        wordsList.add(word)
    }

    override fun removeItem(word: TextWord) {
        wordsList.remove(word)
    }

    override fun moveToCategory() {
        TODO("Not yet implemented")
    }
}
