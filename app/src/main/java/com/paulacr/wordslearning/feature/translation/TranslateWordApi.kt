package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Translations
import com.paulacr.wordslearning.network.ApiService
import io.reactivex.rxjava3.core.Single

class TranslateWordApi(private val api: ApiService) : TranslateWordDataSource {
    override fun translateWord(from: Language, to: Language, text: String): Single<Translations> = api.translateWord(from, to, text)
}
