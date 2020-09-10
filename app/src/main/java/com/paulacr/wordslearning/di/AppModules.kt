package com.paulacr.wordslearning.di

import com.paulacr.wordslearning.data.WordChecker
import com.paulacr.wordslearning.db.WordDao
import com.paulacr.wordslearning.feature.translation.WordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModules {

    @Provides
    fun provideTranslateWorldRepository(wordDao: WordDao): WordRepositoryImpl {
        return WordRepositoryImpl(wordDao)
    }

    @Singleton
    @Provides
    fun provideWordChecker(): WordChecker {
        return WordChecker()
    }
}
