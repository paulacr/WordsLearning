package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Translations
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TranslateWordRepositoryImpl(private val api: TranslateWordApi) : TranslateWordRepository {

    override fun translateWord(from: Language, to: Language, text: String): Single<Translations> = api.translateWord(from, to, text)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
