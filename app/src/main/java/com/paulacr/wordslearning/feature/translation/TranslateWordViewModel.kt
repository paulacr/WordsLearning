package com.paulacr.wordslearning.feature.translation

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.common.Exceptions
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.feature.translation.TranslationState.DISABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ENABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ERROR
import com.paulacr.wordslearning.feature.translation.TranslationState.FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.ON_DOWNLOADING_LANGUAGES_FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.ON_DOWNLOADING_LANGUAGES_STARTED
import com.paulacr.wordslearning.feature.translation.TranslationState.STARTED
import com.paulacr.wordslearning.feature.wordslist.WordsListRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

enum class TranslationState {
    ON_DOWNLOADING_LANGUAGES_STARTED,
    ON_DOWNLOADING_LANGUAGES_FINISHED,
    ENABLED,
    DISABLED,
    STARTED,
    FINISHED,
    ERROR
}

class TranslateWordViewModel(
    private val translateRepository: TranslateWordRepository,
    private val wordsListRepository: WordsListRepository,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    val translation: MutableLiveData<TranslationState> = MutableLiveData()
    var fromLanguage: Language = Language.ENGLISH
    var toLanguage: Language = Language.RUSSIAN

    var textTranslated = ObservableField("")

    init {
        subscribeTranslator()
    }

    private fun subscribeTranslator() {
        compositeDisposable.add(
            translateRepository.subscribeToTranslator().subscribe(translateResult(), onError())
        )

        postValue(ON_DOWNLOADING_LANGUAGES_STARTED)
        compositeDisposable.add(
            translateRepository.subscribeToDownloadLanguages()
                .subscribe(onDownloadCompleted(), onError())
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

    private fun onDownloadCompleted(): Consumer<Unit> = Consumer {
        // do something
        postValue(ON_DOWNLOADING_LANGUAGES_FINISHED)
        Log.i("Log download languages", "completed")
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

    fun onSaveWord(word: String) {
//        compositeDisposable.add(
//            wordsListRepository.addTextWord(word).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe())
    }

    private fun onError() = Consumer<Throwable> {
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
