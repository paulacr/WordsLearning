package com.paulacr.wordslearning.feature.translation

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.common.Exceptions
import com.paulacr.wordslearning.data.Language
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

enum class TranslationState {
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
        translation.postValue(TranslationState.STARTED)
        translateRepository.translateWord(
            fromLanguage,
            toLanguage,
            text ?: throw Exceptions.EmptyTextToTranslate()
        )
    }

    private fun translateResult(): Consumer<String> = Consumer {
        translation.postValue(TranslationState.FINISHED)
        textTranslated.set(it)
    }

    private fun translationError() = Consumer<Throwable> {
    }

    fun onTextChanged(text: String?) {
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
