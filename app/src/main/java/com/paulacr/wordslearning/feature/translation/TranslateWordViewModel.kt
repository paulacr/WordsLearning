package com.paulacr.wordslearning.feature.translation

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.common.Exceptions
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.feature.translation.TranslationState.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

enum class TranslationState {
    ENABLED,
    DISABLED,
    STARTED,
    FINISHED,
    ERROR
}

class TranslateWordViewModel(
    private val translateRepository: TranslateWordRepository,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    val translation: MutableLiveData<TranslationState> = MutableLiveData()
    var fromLanguage: Language = Language.ENGLISH
    var toLanguage: Language = Language.ENGLISH

    var textTranslated = ObservableField("")

    init {
        subscribeTranslator()
    }

    private fun subscribeTranslator() {
        compositeDisposable.add(
            translateRepository.subscribeToTranslator(
                translateResult(),
                translationError()
            )
        )
    }

    fun onTranslate(text: String?) {
        translation.postValue(STARTED)
        translateRepository.translateWord(
            fromLanguage,
            toLanguage,
            text ?: throw Exceptions.EmptyTextToTranslate()
        )
    }

    private fun translateResult(): Consumer<String> = Consumer {
        translation.postValue(FINISHED)
        textTranslated.set(it)
    }

    fun onTranslateWordTextChanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {
        if (s.isNotEmpty()) translation.postValue(ENABLED)
        else translation.postValue(DISABLED)
    }

    private fun translationError() = Consumer<Throwable> {
        translation.postValue(ERROR)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
