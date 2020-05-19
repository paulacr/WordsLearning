package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.feature.translation.TranslateWordApi
import com.paulacr.wordslearning.feature.translation.TranslateWordDataSource
import com.paulacr.wordslearning.feature.translation.TranslateWordRepository
import com.paulacr.wordslearning.feature.translation.TranslateWordRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val translateWordDataSourceModule = module {

    single(named<TranslateWordApi>()) {
        TranslateWordApi(get())
    } bind TranslateWordDataSource::class

    single {
        TranslateWordRepositoryImpl()
    } bind TranslateWordRepository::class
}
