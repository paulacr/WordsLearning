package com.paulacr.wordslearning.feature.translation

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

interface TranslateWordRepository {

    fun subscribeToTranslator(
        result: Consumer<in String>,
        error: Consumer<in Throwable>
    ) : Disposable

    fun translateWord(word: String): Unit
}
