package com.paulacr.wordslearning.data

import okhttp3.internal.toImmutableList

open class WordItem

private var listItems = mutableListOf<WordItem>()
fun buildWordItems(vararg elements: Array<out WordItem>): List<WordItem> {
    elements.forEach {
        listItems.addAll(it)
    }
    return listItems.toImmutableList()
}
