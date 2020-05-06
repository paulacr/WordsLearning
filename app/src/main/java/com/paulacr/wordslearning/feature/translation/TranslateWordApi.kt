package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Repo
import com.paulacr.wordslearning.network.ApiService
import io.reactivex.rxjava3.core.Single

class TranslateWordApi(private val api: ApiService) : TranslateWordDataSource {
    override fun getRepos(): Single<List<Repo>> = api.listAll("paulacr")
}
