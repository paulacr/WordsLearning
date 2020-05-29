package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class TranslateWordRepositoryImpl : TranslateWordRepository {
    private var translateSubject: PublishSubject<String> = PublishSubject.create()
    private var downloadLanguages: PublishSubject<Unit> = PublishSubject.create()

    override fun subscribeToTranslator(
        result: Consumer<in String>,
        error: Consumer<in Throwable>
    ): Disposable =
        translateSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result, error)

    override fun subscribeToDownloadLanguages(
        result: Consumer<in Unit>,
        error: Consumer<in Throwable>
    ): Disposable =
        downloadLanguages
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                downloadDefaultLanguages()
            }
            .subscribe(result, error)

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
