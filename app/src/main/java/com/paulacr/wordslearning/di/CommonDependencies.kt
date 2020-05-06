package com.paulacr.wordslearning.di

import android.content.Context
import com.google.gson.GsonBuilder
import org.koin.dsl.module

internal val commonDependencies = module {
    single { GsonBuilder().create() }

    single { get<Context>().applicationContext.resources }
}
