package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

interface TranslateWordRepository {

    fun subscribeToTranslator(): Observable<String>

    fun subscribeToDownloadLanguages(): Observable<Unit>

    fun translateWord(from: Language, to: Language, word: String)
}
