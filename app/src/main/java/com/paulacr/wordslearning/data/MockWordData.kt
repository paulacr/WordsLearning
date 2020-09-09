package com.paulacr.wordslearning.data

class MockWordData {

    fun getMockedWordsList(): List<Word> = listOf(
        Word(1, "Привет", Language.RUSSIAN),
        Word(2, "кот", Language.RUSSIAN),
        Word(3, "нос", Language.RUSSIAN)
    )
}
