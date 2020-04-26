package com.paulacr.wordslearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import com.paulacr.wordslearning.translation.TranslateWordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModel<TranslateWordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // force crash
        testCrash.setOnClickListener {
            viewModel.test()
            Snackbar.make(it, "Crash fired", Snackbar.LENGTH_LONG).show()
            testCrash()
        }
    }

    private fun testCrash() = Crashlytics.getInstance().crash()
}
