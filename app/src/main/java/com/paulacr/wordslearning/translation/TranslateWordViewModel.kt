package com.paulacr.wordslearning.translation

import android.util.Log
import androidx.lifecycle.ViewModel

class TranslateWordViewModel(private val translateRepository: TranslateWordRepository) : ViewModel() {

    fun test() {
        Log.i("DI", "this sh*t works")
    }
}
