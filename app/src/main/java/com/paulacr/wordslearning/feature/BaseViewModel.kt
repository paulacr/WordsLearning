package com.paulacr.wordslearning.feature

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField

class BaseViewModel : BaseObservable() {

    val data = ObservableField<String>()

    @Bindable
    fun getTranlatedWord(): String? {
        return data.get()
    }
}
