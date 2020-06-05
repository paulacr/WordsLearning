package com.paulacr.wordslearning.feature.wordslist

import com.paulacr.wordslearning.data.TextWord
import io.reactivex.Single

class WordsListRepositoryImpl(private val localDataSource: LocalWordListDataSource) : WordsListRepository {

//    var wordsList = mutableListOf<TextWord>()

    override fun addTextWord(textWord: TextWord) =
//        if (wordsList.contains(word)) throw Exceptions.WordAlreadySavedException()
//        wordsList.add(word)
        localDataSource.addTextWord(textWord)

    override fun removeItem(word: TextWord) {
//        wordsList.remove(word)
    }

    override fun moveToCategory() {
        TODO("Not yet implemented")
    }

    override fun getWordListItems(): Single<List<TextWord>> = localDataSource.getWordListItems()
}
