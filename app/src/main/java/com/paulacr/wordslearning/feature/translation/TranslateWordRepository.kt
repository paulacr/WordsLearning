package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

interface TranslateWordRepository {

    fun subscribeToTranslator(
        result: Consumer<in String>,
        error: Consumer<in Throwable>
    ): Disposable

    fun translateWord(from: Language, to: Language, word: String): Unit
}
