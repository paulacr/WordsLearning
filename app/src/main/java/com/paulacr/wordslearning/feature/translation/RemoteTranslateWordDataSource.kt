package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

interface RemoteTranslateWordDataSource {
    fun getDownloadSubject(): Observable<Pair<Language, Language>>

    fun downloadLanguage(from: Language, to: Language)
}
