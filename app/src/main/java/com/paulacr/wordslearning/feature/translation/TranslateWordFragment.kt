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
import com.google.android.material.snackbar.Snackbar
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.databinding.FragmentTranslateWordBinding
import com.paulacr.wordslearning.feature.translation.TranslationState.DISABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ENABLED
import com.paulacr.wordslearning.feature.translation.TranslationState.ERROR
import com.paulacr.wordslearning.feature.translation.TranslationState.FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.ON_DOWNLOADING_LANGUAGES_FINISHED
import com.paulacr.wordslearning.feature.translation.TranslationState.ON_DOWNLOADING_LANGUAGES_STARTED
import com.paulacr.wordslearning.feature.translation.TranslationState.STARTED
import com.paulacr.wordslearning.ui.LanguageSelectorView
import com.paulacr.wordslearning.ui.OnLanguageSelected
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

    override fun onResume() {
        super.onResume()
        setupObservers()
        disableTranslateButton()
        languageSelectorView = binding.languageSelector
        languageSelectorView.setListener(this)
    }

    override fun onDestroy() {
        languageSelectorView.setListener(null)
        super.onDestroy()
    }

    private fun enableTranslateButton() {
        changeTranslateButtonPlaceHolderColor(R.color.green_light)
        binding.translateButtonPlaceholder.isEnabled = true
    }

    private fun disableTranslateButton() {
        changeTranslateButtonPlaceHolderColor(R.color.gray)
        binding.translateButtonPlaceholder.isEnabled = false
    }

    private fun changeTranslateButtonPlaceHolderColor(@ColorRes color: Int) {
        val vector =
            VectorChildFinder(context, R.drawable.ic_tick, binding.translateButtonPlaceholder)
        val path1 = vector.findPathByName("path_1")
        context?.let {
            path1.fillColor = ContextCompat.getColor(it, color)
        }
    }

    private fun animateView(view: View, @AnimRes animation: Int) {
        view.startAnimation(AnimationUtils.loadAnimation(context, animation))
    }

    private fun setupObservers() {
        val downloadSnackBar = Snackbar.make(binding.root, "Downloading Languages", Snackbar.LENGTH_INDEFINITE)
        translationObserver = Observer<TranslationState> {
            when (it) {
                ON_DOWNLOADING_LANGUAGES_STARTED -> {
                    downloadSnackBar.show()
                }

                ON_DOWNLOADING_LANGUAGES_FINISHED -> {
                    downloadSnackBar.dismiss()
                }
                ENABLED -> {
                    enableTranslateButton()
                    binding.clearTextButton.visibility = View.VISIBLE
                }
                DISABLED -> {
                    disableTranslateButton()
                    binding.wordFieldEditText.text.clear()
                    binding.clearTextButton.visibility = View.GONE
                    binding.translateAnimation.visibility = View.GONE

                    if (binding.textTranslated.visibility == View.VISIBLE) {
                        animateView(binding.textTranslated, R.anim.fade_out)
                        animateView(binding.saveWord, R.anim.fade_out)
                        binding.textTranslated.visibility = View.INVISIBLE
                        binding.saveWord.visibility = View.INVISIBLE
                    }
                }
                STARTED -> {
                    binding.translateAnimation.visibility = View.VISIBLE
                    binding.translateAnimation.playAnimation()
                }
                FINISHED -> {
                    binding.textTranslated.visibility = View.VISIBLE
                    binding.saveWord.visibility = View.VISIBLE
                    animateView(binding.textTranslated, R.anim.fade_in)
                    animateView(binding.saveWord, R.anim.fade_in)
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
                TranslateWordFragmentDirections.actionFragmentWordsList()
            )
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
