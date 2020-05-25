package com.paulacr.wordslearning.feature.translation

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.crashlytics.android.Crashlytics
import com.devs.vectorchildfinder.VectorChildFinder
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.databinding.ActivityMainBinding
import com.paulacr.wordslearning.feature.translation.TranslationState.DISABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ENABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ERROR
import com.paulacr.wordslearning.feature.translation.TranslationState.FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.STARTED
import com.paulacr.wordslearning.ui.LanguageSelectorView
import com.paulacr.wordslearning.ui.OnLanguageSelected
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateWordActivity : AppCompatActivity(), OnLanguageSelected {

    private val viewModel by viewModel<TranslateWordViewModel>()
    private lateinit var translationObserver: Observer<TranslationState>
    private lateinit var languageSelectorView: LanguageSelectorView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        setupObservers()
        setOnClearedTextListener()
        disableTranslateButton()
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

    private fun enableTranslateButton() {
        changeTranslateButtonPlaceHolderColor(R.color.green_light)
        translateButtonPlaceholder.isEnabled = true
    }

    private fun disableTranslateButton() {
        changeTranslateButtonPlaceHolderColor(R.color.gray)
        translateButtonPlaceholder.isEnabled = false
    }

    private fun changeTranslateButtonPlaceHolderColor(@ColorRes color: Int) {
        val vector = VectorChildFinder(this, R.drawable.ic_tick, translateButtonPlaceholder)
        val path1 = vector.findPathByName("path_1")
        path1.fillColor = ContextCompat.getColor(this, color)
    }

    private fun animateView(view: View, @AnimRes animation: Int) {
        view.startAnimation(AnimationUtils.loadAnimation(this, animation))
    }

    private fun setOnClearedTextListener() {
        wordField.setEndIconOnClickListener {
            if (textTranslated.visibility == View.VISIBLE) {
                animateView(textTranslated, R.anim.fade_out)
                textTranslated.visibility = View.GONE
            }
            changeTranslateButtonPlaceHolderColor(R.color.gray)
            wordFieldEditText.setText("")
            translateAnimation.visibility = View.GONE
        }
    }

    private fun setupObservers() {
        translationObserver = Observer<TranslationState> {
            when (it) {
                ENABLED -> enableTranslateButton()
                DISABLED -> disableTranslateButton()
                STARTED -> {
                    translateAnimation.visibility = View.VISIBLE
                    translateAnimation.playAnimation()
                }
                FINISHED -> {
                    textTranslated.visibility = View.VISIBLE
                    animateView(textTranslated, R.anim.fade_in)
                }
                ERROR -> {
                    // show snack bar with error
                }
            }
        }
        viewModel.translation.observe(this, translationObserver)
    }

    private fun testCrash() = Crashlytics.getInstance().crash()

    override fun onFromLanguageSelected(language: Language) {
        viewModel.fromLanguage = language
    }

    override fun onToLanguageSelected(language: Language) {
        viewModel.toLanguage = language
    }
}
