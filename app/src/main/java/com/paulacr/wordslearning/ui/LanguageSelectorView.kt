package com.paulacr.wordslearning.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.paulacr.wordslearning.R

class LanguageSelectorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.language_selector_view, this, false)
        val set = ConstraintSet()
        addView(view)

        set.clone(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupSpinners()
    }

    private fun setupSpinners() {

        this.findViewById<Spinner>(R.id.originLanguage).apply {
            adapter = getSpinnerAdapter()
            this.setSelection(1)
        }

        this.findViewById<Spinner>(R.id.translatedLanguage).apply {
            adapter = getSpinnerAdapter()
            this.setSelection(2)
        }
    }

    private fun getSpinnerAdapter(): ArrayAdapter<String> {
        val languages = this.resources.getStringArray(R.array.languages)
        val spinnerAdapter = ArrayAdapter(context, R.layout.custom_spinner_textview, languages)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return spinnerAdapter
    }
}
