package com.paulacr.wordslearning.data

import okhttp3.internal.toImmutableList

enum class WordItemType(val value: Int) {
    TEXT_WORD_TYPE(0),
    IMAGE_WORD_TYPE(1)
}

abstract class WordItem {

    abstract var type: WordItemType
    abstract var fromLanguage: Language
    abstract var toLanguage: Language
}

private var listItems = mutableListOf<WordItem>()
fun buildWordItems(vararg elements: Array<out WordItem>): List<WordItem> {
    elements.forEach {
        listItems.addAll(it)
    }
    return listItems.toImmutableList()
}
