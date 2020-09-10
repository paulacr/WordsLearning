package com.paulacr.wordslearning.feature.translation

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Language.ENGLISH
import com.paulacr.wordslearning.data.Language.RUSSIAN
import com.paulacr.wordslearning.data.WordChecker
import com.paulacr.wordslearning.feature.translation.TranslationState.CORRECT
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
    ERROR,
    CORRECT
}

class TranslateWordViewModel @ViewModelInject constructor(
    private val repository: WordRepository,
    private val wordChecker: WordChecker
) : ViewModel() {

    companion object {
        @BindingAdapter("android:text")
        @JvmStatic
        fun setWord(view: EditText, newValue: String?) {
            // Important to break potential infinite loops.
            if (view.text.toString() != newValue) {
                view.setText(newValue)
            }
        }
    }

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val translation: MutableLiveData<TranslationState> = MutableLiveData()
    var fromLanguage: Language = ENGLISH
    var toLanguage: Language = RUSSIAN

    var word = ObservableField("")
    var translationField = ObservableField<String>()
    var isCorrectTranslation = ObservableBoolean(false)
    var showResult = ObservableBoolean(false)

    fun showWordToTranslate() {
        uiScope.launch {
            val randomWord = repository.getRandomWord()
            postValue(FINISHED)
            this@TranslateWordViewModel.word.set(randomWord.word)
        }
    }

    fun onTranslate() = translationResult().apply {
        isCorrectTranslation.set(this)
        if (this) postValue(CORRECT)
        else postValue(ERROR)
        showResult.set(true)
    }

    private fun translationResult() =
        wordChecker.isCorrectTranslation(word.get().toString(), translationField.get().toString())

//    private fun translateResult(): Consumer<String> = Consumer {
//        postValue(FINISHED)
//        word.set(it)
//    }

    fun onTranslateWordTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (s.isNotEmpty()) {
            postValue(ENABLED)
            translationField.set(s.toString())
        } else postValue(DISABLED)
    }

    fun onClearTextClicked() {
        postValue(DISABLED)
        showResult.set(false)
        translationField.set("")
        showWordToTranslate()
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
