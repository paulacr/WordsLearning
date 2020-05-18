package com.paulacr.wordslearning.feature.translation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.Language
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

class TranslateWordViewModel(
    private val translateRepository: TranslateWordRepository,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    val translation: MutableLiveData<String> = MutableLiveData()
    var fromLanguage: Language = Language.ENGLISH
    var toLanguage: Language = Language.ENGLISH

    init {
        subscribeTranslator()
    }

    private fun subscribeTranslator() {
        compositeDisposable.add(translateRepository.subscribeToTranslator(
            translateResult(),
            translationError()
        ))
    }

    fun translateWord(word: String) {
        translateRepository.translateWord(word)
    }

    private fun translateResult(): Consumer<String> = Consumer {
        translation.postValue(it)
        Log.i("Repos list", it.toString())
    }

    private fun translationError() = Consumer<Throwable> {
        Log.i("Repos error", it.localizedMessage ?: "")
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
