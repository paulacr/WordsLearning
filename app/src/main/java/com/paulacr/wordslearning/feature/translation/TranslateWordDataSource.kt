package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Translations
import io.reactivex.rxjava3.core.Single

interface TranslateWordDataSource {
    fun translateWord(from: Language, to: Language, text: String): Single<Translations>

    fun translateWithFirebase(text: String): Single<String>
}
