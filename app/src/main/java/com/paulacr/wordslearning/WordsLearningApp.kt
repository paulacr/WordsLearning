package com.paulacr.wordslearning

import android.app.Application
import com.paulacr.wordslearning.di.commonDependencies
import com.paulacr.wordslearning.di.networkDependencies
import com.paulacr.wordslearning.di.translateWordDataSourceModule
import com.paulacr.wordslearning.di.translateWordViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordsLearningApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordsLearningApp)
            modules(listOf(
                commonDependencies,
                networkDependencies,
                translateWordViewModelModule,
                translateWordDataSourceModule
            ))
        }
    }
}
