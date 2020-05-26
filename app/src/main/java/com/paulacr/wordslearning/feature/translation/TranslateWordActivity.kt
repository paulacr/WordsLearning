package com.paulacr.wordslearning.feature.translation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.paulacr.wordslearning.R

class TranslateWordActivity : AppCompatActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbarView = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbarView)
        toolbarView.setupWithNavController(navController, appBarConfiguration)

        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, TranslateWordFragment()).commit()

//        supportFragmentManager.beginTransaction()
//            .add(R.id.wordListContainer, WordsListFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
