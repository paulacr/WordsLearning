package com.paulacr.wordslearning.feature.wordslist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.wordslearning.data.Word
import com.paulacr.wordslearning.feature.translation.TranslationState
import com.paulacr.wordslearning.feature.translation.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddWordViewModel @ViewModelInject constructor(private val wordRepository: WordRepository) :
    ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val state: MutableLiveData<TranslationState> = MutableLiveData()

    fun onSaveWord(word: Word) =
        uiScope.launch { wordRepository.addWord(word) }
            .invokeOnCompletion {
                postValue(TranslationState.FINISHED)
            }

    private fun postValue(newState: TranslationState) {
        if (state.value == newState) return
        else state.postValue(newState)
    }
}