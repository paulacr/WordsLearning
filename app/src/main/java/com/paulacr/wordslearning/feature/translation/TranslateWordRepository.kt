package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Repo
import io.reactivex.rxjava3.core.Single

interface TranslateWordRepository {

    fun getRepos(): Single<List<Repo>>
}
