package com.paulacr.wordslearning.common

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

class BindingAdapters {

    @BindingAdapter("app:textChangedListener")
    fun onTextChanged(et: EditText, number: Int) {
        et.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (et.text.toString().trim().length >= number) {
                    et.setBackgroundColor(Color.GREEN)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }
}

interface OnTextChanged {
    fun onTextEdited(charSequence: CharSequence)
}
