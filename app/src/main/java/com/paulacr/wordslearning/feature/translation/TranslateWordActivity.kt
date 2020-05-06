package com.paulacr.wordslearning.feature.translation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.Repo
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateWordActivity : AppCompatActivity() {

    val viewModel by viewModel<TranslateWordViewModel>()
    lateinit var reposObserver: Observer<List<Repo>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()

        textView.setOnClickListener {
            viewModel.getRepos()
            Snackbar.make(it, "getting repos", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setupObservers() {
        reposObserver = Observer<List<Repo>> {
            textView.text = it.joinToString(",")
        }
        viewModel.repos.observe(this, reposObserver)
    }

    private fun testCrash() = Crashlytics.getInstance().crash()
}
