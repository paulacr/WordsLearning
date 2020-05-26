package com.paulacr.wordslearning.feature.translation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.crashlytics.android.Crashlytics
import com.devs.vectorchildfinder.VectorChildFinder
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.databinding.FragmentTranslateWordBinding
import com.paulacr.wordslearning.feature.translation.TranslationState.DISABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ENABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ERROR
import com.paulacr.wordslearning.feature.translation.TranslationState.FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.STARTED
import com.paulacr.wordslearning.ui.LanguageSelectorView
import com.paulacr.wordslearning.ui.OnLanguageSelected
import kotlinx.android.synthetic.main.fragment_translate_word.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateWordFragment : Fragment(), OnLanguageSelected {

    private val viewModel by viewModel<TranslateWordViewModel>()
    private lateinit var translationObserver: Observer<TranslationState>
    private lateinit var languageSelectorView: LanguageSelectorView
    private lateinit var binding: FragmentTranslateWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_translate_word, container, false)
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        setOnClearedTextListener()
        disableTranslateButton()
    }

    override fun onResume() {
        super.onResume()
        languageSelectorView = languageSelector
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
        val vector = VectorChildFinder(context, R.drawable.ic_tick, translateButtonPlaceholder)
        val path1 = vector.findPathByName("path_1")
        context?.let {
            path1.fillColor = ContextCompat.getColor(it, color)
        }
    }

    private fun animateView(view: View, @AnimRes animation: Int) {
        view.startAnimation(AnimationUtils.loadAnimation(context, animation))
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
        viewModel.translation.observe(viewLifecycleOwner, translationObserver)
    }

    private fun testCrash() = Crashlytics.getInstance().crash()

    override fun onFromLanguageSelected(language: Language) {
        viewModel.fromLanguage = language
    }

    override fun onToLanguageSelected(language: Language) {
        viewModel.toLanguage = language
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_words_list) {
            findNavController().navigate(
                TranslateWordFragmentDirections.actionFragmentWordsList())
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
