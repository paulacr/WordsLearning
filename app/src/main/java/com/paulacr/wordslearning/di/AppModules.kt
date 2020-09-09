package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.db.WordDao
import com.paulacr.wordslearning.feature.translation.WordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AppModules {

    @Provides
    fun provideTranslateWorldRepository(wordDao: WordDao): WordRepositoryImpl {
        return WordRepositoryImpl(wordDao)
    }
}
