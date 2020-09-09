package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.feature.translation.WordRepository
import com.paulacr.wordslearning.feature.translation.WordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class BindModules {

    @Binds
    abstract fun bindTranslateRepository(repository: WordRepositoryImpl): WordRepository
}
