package com.paulacr.wordslearning.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.Language

class LanguageSelectorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : ConstraintLayout(context, attrs, defStyleAttr), AdapterView.OnItemSelectedListener {

    private lateinit var spinnerAdapter: ArrayAdapter<Language>
    private var onLanguageSelected: OnLanguageSelected? = null
    private val languages = Language.values()

    init {
        val view =
            LayoutInflater.from(context).inflate(R.layout.language_selector_view, this, false)
        val set = ConstraintSet()
        addView(view)

        set.clone(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupSpinners()
    }

    fun setListener(callback: OnLanguageSelected?) {
        this.onLanguageSelected = callback
    }

    private fun setupSpinners() {

        this.findViewById<Spinner>(R.id.fromLanguage).apply {
                adapter = getSpinnerAdapter()
                this.setSelection(1)
                this.onItemSelectedListener = this@LanguageSelectorView
            }

        this.findViewById<Spinner>(R.id.toLanguage).apply {
                adapter = getSpinnerAdapter()
                this.setSelection(2)
                this.onItemSelectedListener = this@LanguageSelectorView
            }
    }

    private fun getSpinnerAdapter(): ArrayAdapter<Language> {
        spinnerAdapter = ArrayAdapter(context, R.layout.custom_spinner_textview, languages)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return spinnerAdapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent?.id == R.id.fromLanguage) {
            onLanguageSelected?.onFromLanguageSelected(languages[position])
        } else {
            onLanguageSelected?.onToLanguageSelected(languages[position])
        }
    }
}

interface OnLanguageSelected {
    fun onFromLanguageSelected(language: Language)
    fun onToLanguageSelected(language: Language)
}
