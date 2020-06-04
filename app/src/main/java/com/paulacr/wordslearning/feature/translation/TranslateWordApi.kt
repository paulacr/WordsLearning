package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class TranslateWordApi : RemoteTranslateWordDataSource {

    private var translateSubject: PublishSubject<String> = PublishSubject.create()
    private var downloadLanguages: PublishSubject<Pair<Language, Language>> = PublishSubject.create()

    override fun subscribeToTranslator(): Observable<String> =
        translateSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun subscribeToDownloadLanguages(): Observable<Pair<Language, Language>> =
        downloadLanguages
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun translateWord(from: Language, to: Language, word: String) {
        getTranslator(from, to).translate(word)
            .addOnSuccessListener { translatedWord ->
                translateSubject.onNext(translatedWord)
            }
            .addOnFailureListener {
                translateSubject.onError(it)
            }
    }

    override fun downloadLanguage(from: Language, to: Language) {
        getTranslator(from, to).downloadModelIfNeeded()
            .addOnSuccessListener {
                downloadLanguages.onNext(from to to)
            }.addOnFailureListener {
                downloadLanguages.onError(it)
            }
    }
}
