package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LocalTranslateWordDataSourceImpl : LocalTranslateWordDataSource {

    private var translateSubject: PublishSubject<String> = PublishSubject.create()

    override fun getTranslateSubject(): Observable<String> = translateSubject

    override fun translateWord(from: Language, to: Language, word: String) {
        getTranslator(from, to).translate(word)
            .addOnSuccessListener { translatedWord ->
                translateSubject.onNext(translatedWord)
            }
            .addOnFailureListener {
                translateSubject.onError(it)
            }
    }
}
