package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Repo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TranslateWordRepositoryImpl(private val api: TranslateWordApi) : TranslateWordRepository {

    override fun getRepos(): Single<List<Repo>> = api.getRepos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
