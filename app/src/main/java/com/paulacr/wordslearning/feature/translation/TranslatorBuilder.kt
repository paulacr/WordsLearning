package com.paulacr.wordslearning.feature.translation

import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions

val options = FirebaseTranslatorOptions.Builder()
    .setSourceLanguage(FirebaseTranslateLanguage.EN)
    .setTargetLanguage(FirebaseTranslateLanguage.RU)
    .build()

val translator = FirebaseNaturalLanguage.getInstance().getTranslator(options)

