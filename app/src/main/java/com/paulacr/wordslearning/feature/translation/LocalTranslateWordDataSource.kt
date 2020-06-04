package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

interface LocalTranslateWordDataSource {
    fun getTranslateSubject(): Observable<String>

    fun translateWord(from: Language, to: Language, word: String)
}
