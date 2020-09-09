package com.paulacr.wordslearning.feature.translation

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Language.ENGLISH
import com.paulacr.wordslearning.data.Language.RUSSIAN
import com.paulacr.wordslearning.feature.translation.TranslationState.DISABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ENABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ERROR
import com.paulacr.wordslearning.feature.translation.TranslationState.FINISHED
import io.reactivex.functions.Consumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

enum class TranslationState {
    ON_DOWNLOADING_LANGUAGES_STARTED,
    ON_DOWNLOADING_LANGUAGES_FINISHED,
    ENABLED,
    DISABLED,
    STARTED,
    FINISHED,
    ERROR
}

class TranslateWordViewModel @ViewModelInject constructor(
    private val repository: WordRepository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val translation: MutableLiveData<TranslationState> = MutableLiveData()
    var fromLanguage: Language = ENGLISH
    var toLanguage: Language = RUSSIAN

    var word = ObservableField("")

    fun showWordToTranslate() {
        uiScope.launch {
            val word = repository.getRandomWord()
            postValue(FINISHED)
            this@TranslateWordViewModel.word.set(word.name)
        }
    }

    private fun translateResult(): Consumer<String> = Consumer {
        postValue(FINISHED)
        word.set(it)
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

    private fun onError() = Consumer<Throwable> {
        postValue(ERROR)
    }

    private fun postValue(state: TranslationState) {
        if (translation.value == state) return
        else translation.postValue(state)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
