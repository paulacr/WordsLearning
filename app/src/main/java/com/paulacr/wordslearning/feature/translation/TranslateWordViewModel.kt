package com.paulacr.wordslearning.feature.translation

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.common.Exceptions
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.feature.translation.TranslationState.DISABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ENABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ERROR
import com.paulacr.wordslearning.feature.translation.TranslationState.FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.STARTED
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

enum class TranslationState {
    ENABLED,
    DISABLED,
    STARTED,
    FINISHED,
    CLEARED,
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
        postValue(STARTED)
        translateRepository.translateWord(
            fromLanguage,
            toLanguage,
            text ?: throw Exceptions.EmptyTextToTranslate()
        )
    }

    private fun translateResult(): Consumer<String> = Consumer {
        postValue(FINISHED)
        textTranslated.set(it)
    }

    fun onTranslateWordTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (s.isNotEmpty()) postValue(ENABLED)
        else postValue(DISABLED)
    }

    fun onClearTextClicked() {
        postValue(DISABLED)
    }

    private fun translationError() = Consumer<Throwable> {
        postValue(ERROR)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun postValue(state: TranslationState) {
        if (translation.value == state) return
        else translation.postValue(state)
    }
}
