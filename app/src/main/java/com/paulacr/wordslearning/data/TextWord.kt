package com.paulacr.wordslearning.data

data class TextWord(
    val id: Int, val name: String, val pronunciation: String?,
    val translation: String
) : WordItem()