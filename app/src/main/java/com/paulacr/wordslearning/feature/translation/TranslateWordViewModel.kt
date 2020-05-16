package com.paulacr.wordslearning.feature.translation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Text
import com.paulacr.wordslearning.data.Translations
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

class TranslateWordViewModel(
    private val translateRepository: TranslateWordRepository,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    val translation: MutableLiveData<List<Text>> = MutableLiveData()
    var fromLanguage: Language = Language.ENGLISH
    var toLanguage: Language = Language.ENGLISH

    fun translateWord(text: String): Boolean {
        Log.i("Log translate word", "text=$text")
        return compositeDisposable.add(translateRepository.translateWord(fromLanguage, toLanguage, text).subscribe(translateResult(), reposError()))
    }

    private fun translateResult(): Consumer<Translations> = Consumer {
        translation.postValue(it.text)
        Log.i("Repos list", it.toString())
    }

    private fun reposError() = Consumer<Throwable> {
        Log.i("Repos error", it.localizedMessage ?: "")
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
