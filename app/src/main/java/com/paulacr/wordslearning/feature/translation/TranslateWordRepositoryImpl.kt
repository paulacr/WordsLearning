package com.paulacr.wordslearning.feature.translation

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class TranslateWordRepositoryImpl : TranslateWordRepository {
    var translateSubject: PublishSubject<String> = PublishSubject.create()

    override fun subscribeToTranslator(
        result: Consumer<in String>,
        error: Consumer<in Throwable>
    ) : Disposable =
        translateSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result, error)

    override fun translateWord(word: String) {
        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                translator.translate("Hi")
                    .addOnSuccessListener {
                        translateSubject.onNext(it)
                    }
                    .addOnFailureListener {
                        Single.error<Exception>(Exception("xxxx"))
                    }
            }

            .addOnFailureListener {
                Single.error<Exception>(Exception("xxxx"))
            }
    }
}
