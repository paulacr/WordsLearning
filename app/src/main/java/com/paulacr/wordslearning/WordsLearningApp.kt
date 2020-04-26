package com.paulacr.wordslearning

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.paulacr.wordslearning.di.RepositoryModules
import com.paulacr.wordslearning.di.translateWordViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordsLearningApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordsLearningApp)
            modules(listOf(
                translateWordViewModelModule
            ))
            RepositoryModules.init()
        }
    }
}