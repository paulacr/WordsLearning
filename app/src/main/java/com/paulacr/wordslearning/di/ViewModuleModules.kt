package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.feature.translation.TranslateWordViewModel
import org.koin.dsl.module

val translateWordViewModelModule = module {
    single { TranslateWordViewModel(translateRepository = get()) }
}
