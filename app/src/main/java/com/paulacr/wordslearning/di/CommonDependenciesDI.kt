package com.paulacr.wordslearning.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.paulacr.wordslearning.common.DownloadLanguageSharedPrefs
import com.paulacr.wordslearning.db.AppDataBase
import org.koin.dsl.module

internal val commonDependencies = module {
    single { GsonBuilder().create() }

    single { get<Context>().applicationContext.resources }

    single {
        Room.databaseBuilder(
            get<Context>().applicationContext,
            AppDataBase::class.java,
            "words-learning-db"
        )
            .build()
    }

    single { get<AppDataBase>().textWordDao() }

    single { get<AppDataBase>().imageWordDao() }

    single { DownloadLanguageSharedPrefs(get())}
}
