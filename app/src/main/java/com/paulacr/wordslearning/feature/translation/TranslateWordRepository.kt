package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

interface TranslateWordRepository {

    fun getTranslateSubject(): Observable<String>

    fun getDownloadSubject(): Observable<Pair<Language, Language>>

    fun translateWord(from: Language, to: Language, word: String)

    fun hasDownloadedLanguages(): Boolean

    fun downloadLanguage(from: Language, to: Language)
}
