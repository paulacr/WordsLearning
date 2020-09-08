package com.paulacr.wordslearning.feature.wordslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.TextWord
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WordsListViewModel(private val repository: WordsListRepository) : ViewModel() {

    val wordListItems = MutableLiveData<List<TextWord>>()
    val exception = MutableLiveData<Throwable>()

    init {
        getTextWordItems()
    }

    fun saveTextWordItem() {

    }

    fun getTextWordItems() =
        repository.getWordListItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ wordList ->
                wordListItems.postValue(wordList)
            }, {
                exception.postValue(it)
            })
}
