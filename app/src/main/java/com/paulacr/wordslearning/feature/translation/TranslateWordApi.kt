package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Translations
import com.paulacr.wordslearning.network.ApiService
import io.reactivex.rxjava3.core.Single

class TranslateWordApi(private val api: ApiService) : TranslateWordDataSource
