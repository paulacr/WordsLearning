package com.paulacr.wordslearning.data

import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage

enum class Language(val firebaseCode: Int) {
    PORTUGUESE(FirebaseTranslateLanguage.PT),
    ENGLISH(FirebaseTranslateLanguage.EN),
    RUSSIAN(FirebaseTranslateLanguage.RU)
}
