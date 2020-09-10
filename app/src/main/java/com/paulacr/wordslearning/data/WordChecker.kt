package com.paulacr.wordslearning.data

import java.util.Locale

class WordChecker {

    val mockedData = MockWordData()

    fun isCorrectTranslation(currentWord: String, translatedWord: String): Boolean {
        return mockedData.getMockedWordsList().find {
            it.word == currentWord
        }.takeIf {
            it?.translation?.toLowerCase(Locale.getDefault()) == translatedWord.toLowerCase(Locale.getDefault())
        }?.let {
            return@let true
        } ?: return false
    }
}
