package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.feature.translation.LocalTranslateWordDataSource
import com.paulacr.wordslearning.feature.translation.LocalTranslateWordDataSourceImpl
import com.paulacr.wordslearning.feature.translation.RemoteTranslateWordDataSource
import com.paulacr.wordslearning.feature.translation.TranslateWordApi
import com.paulacr.wordslearning.feature.translation.TranslateWordRepository
import com.paulacr.wordslearning.feature.translation.TranslateWordRepositoryImpl
import com.paulacr.wordslearning.feature.wordslist.LocalWordListDataSource
import com.paulacr.wordslearning.feature.wordslist.LocalWordListDataSourceImpl
import com.paulacr.wordslearning.feature.wordslist.WordsListRepository
import com.paulacr.wordslearning.feature.wordslist.WordsListRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val translateWordDataSourceModule = module {

    single {
        TranslateWordApi()
    } bind RemoteTranslateWordDataSource::class

    single {
        LocalTranslateWordDataSourceImpl()
    } bind LocalTranslateWordDataSource::class

    single {
        TranslateWordRepositoryImpl(get(), get(), get())
    } bind TranslateWordRepository::class

    single {
        WordsListRepositoryImpl(get())
    } bind WordsListRepository::class

    single {
        LocalWordListDataSourceImpl(get(), get())
    } bind LocalWordListDataSource::class
}

val wordListDataSourceModule = module {
}
