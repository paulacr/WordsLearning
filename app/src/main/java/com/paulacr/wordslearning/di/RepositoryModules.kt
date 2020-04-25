package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.translation.TranslateWordRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object RepositoryModules {
    fun init() {
        loadKoinModules(
            listOf(
                translateWordRepository
            )
        )
    }
}

val translateWordRepository = module {
    factory { TranslateWordRepository() }
}