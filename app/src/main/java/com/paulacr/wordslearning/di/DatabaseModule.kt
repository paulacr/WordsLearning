package com.paulacr.wordslearning.di

import android.app.Application
import androidx.room.Room
import com.paulacr.wordslearning.db.AppDataBase
import com.paulacr.wordslearning.db.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providePokemonDB(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "WordDatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideWordDao(appDataBase: AppDataBase): WordDao {
        return appDataBase.wordDao()
    }
}
