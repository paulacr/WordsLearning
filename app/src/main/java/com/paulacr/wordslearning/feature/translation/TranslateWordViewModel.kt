package com.paulacr.wordslearning.feature.translation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.Repo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

class TranslateWordViewModel(
    private val translateRepository: TranslateWordRepository,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    val repos: MutableLiveData<List<Repo>> = MutableLiveData()

    fun getRepos() =
        compositeDisposable.add(translateRepository.getRepos().subscribe(reposResult(), reposError()))

    private fun reposResult(): Consumer<List<Repo>> = Consumer {
        repos.postValue(it)
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
