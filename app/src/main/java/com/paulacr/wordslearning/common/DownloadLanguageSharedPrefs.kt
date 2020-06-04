package com.paulacr.wordslearning.common

import android.content.Context

private const val DOWNLOADED_DEFAULT_LANGUAGES = "downloaded_languages"
class DownloadLanguageSharedPrefs(context: Context) : BaseSharedPrefs(context) {
    override var prefName: String = "DOWNLOAD_LANGUAGES_PREFS"

    fun setDownloadedLanguages() {
        getSharedPref()?.setValue(DOWNLOADED_DEFAULT_LANGUAGES, true)
    }

    fun hasDownloadedLanguages(): Boolean? = getSharedPref()?.get(DOWNLOADED_DEFAULT_LANGUAGES, false)
}
