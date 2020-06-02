package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class TranslateWordApi : RemoteTranslateWordDataSource {

    private var translateSubject: PublishSubject<String> = PublishSubject.create()
    private var downloadLanguages: PublishSubject<Unit> = PublishSubject.create()

    override fun subscribeToTranslator(): Observable<String> =
        translateSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun subscribeToDownloadLanguages(): Observable<Unit> =
        downloadLanguages
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                downloadDefaultLanguages()
            }

    override fun translateWord(from: Language, to: Language, word: String) {
        getTranslator(from, to).translate(word)
            .addOnSuccessListener { translatedWord ->
                translateSubject.onNext(translatedWord)
            }
            .addOnFailureListener {
                translateSubject.onError(it)
            }
    }

    override fun downloadDefaultLanguages() {
        val languageList = listOf(Language.ENGLISH, Language.PORTUGUESE, Language.RUSSIAN)

        getTranslator(languageList[0], languageList[1]).downloadModelIfNeeded()
            .addOnSuccessListener {
                getTranslator(languageList[1], languageList[2]).downloadModelIfNeeded()
                    .addOnSuccessListener {
                        getTranslator(languageList[2], languageList[0]).downloadModelIfNeeded()
                            .addOnSuccessListener {
                                downloadLanguages.onNext(Unit)
                            }
                    }
            }
    }
}
