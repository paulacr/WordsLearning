package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class TranslateWordApi : RemoteTranslateWordDataSource {

    private var downloadLanguages: PublishSubject<Pair<Language, Language>> = PublishSubject.create()

    override fun getDownloadSubject(): Observable<Pair<Language, Language>> = downloadLanguages

    override fun downloadLanguage(from: Language, to: Language) {
        getTranslator(from, to).downloadModelIfNeeded()
            .addOnSuccessListener {
                downloadLanguages.onNext(from to to)
            }.addOnFailureListener {
                downloadLanguages.onError(it)
            }
    }
}
