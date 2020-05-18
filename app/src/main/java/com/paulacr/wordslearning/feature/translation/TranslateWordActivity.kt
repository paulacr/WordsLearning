package com.paulacr.wordslearning.feature.translation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.crashlytics.android.Crashlytics
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.ui.LanguageSelectorView
import com.paulacr.wordslearning.ui.OnLanguageSelected
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.language_selector_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateWordActivity : AppCompatActivity(), OnLanguageSelected {

    private val viewModel by viewModel<TranslateWordViewModel>()
    private lateinit var reposObserver: Observer<String>
    private lateinit var languageSelectorView: LanguageSelectorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()

        textView.setOnClickListener {
            viewModel.translateWord("random text")
//            Snackbar.make(it, "getting repos", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        languageSelectorView = findViewById(R.id.languageSelectorView)
        languageSelectorView.setListener(this)
    }

    override fun onDestroy() {
        languageSelectorView.setListener(null)
        super.onDestroy()
    }

    private fun setupObservers() {
        reposObserver = Observer<String> {
            textView.text = it
        }
        viewModel.translation.observe(this, reposObserver)
    }

    private fun testCrash() = Crashlytics.getInstance().crash()

    override fun onFromLanguageSelected(language: Language) {
        viewModel.fromLanguage = language
    }

    override fun onToLanguageSelected(language: Language) {
        viewModel.toLanguage = language
    }
}
