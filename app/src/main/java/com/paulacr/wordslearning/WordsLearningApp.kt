package com.paulacr.wordslearning

import android.app.Application
import com.facebook.stetho.Stetho
import com.paulacr.wordslearning.di.commonDependencies
import com.paulacr.wordslearning.di.networkDependencies
import com.paulacr.wordslearning.di.translateWordDataSourceModule
import com.paulacr.wordslearning.di.translateWordViewModelModule
import com.paulacr.wordslearning.di.wordListDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordsLearningApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
        startKoin {
            androidContext(this@WordsLearningApp)
            modules(
                listOf(
                    commonDependencies,
                    networkDependencies,
                    translateWordViewModelModule,
                    translateWordDataSourceModule,
                    wordListDataSourceModule
                )
            )
        }
    }

    private fun initializeStetho() {
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)
    }
}
