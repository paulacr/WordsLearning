package com.paulacr.wordslearning.feature.translation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.feature.wordslist.WordsListFragment

class TranslateWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        supportFragmentManager.beginTransaction()
            .add(R.id.translateContainer, TranslateWordFragment()).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.wordListContainer, WordsListFragment()).commit()
    }
}
