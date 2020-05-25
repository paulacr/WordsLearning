package com.paulacr.wordslearning.feature.translation

import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import com.paulacr.wordslearning.common.Exceptions.SetupLanguageMissingException
import com.paulacr.wordslearning.data.Language

private lateinit var translator: FirebaseTranslator
private var options: FirebaseTranslatorOptions? = null

fun getTranslator(from: Language, to: Language): FirebaseTranslator {

    if (options == null || (options?.sourceLanguage != from.firebaseCode) ||
        (options?.targetLanguage != to.firebaseCode)) {
        options = FirebaseTranslatorOptions.Builder()
            .setSourceLanguage(from.firebaseCode)
            .setTargetLanguage(to.firebaseCode)
            .build()

        translator = FirebaseNaturalLanguage.getInstance().getTranslator(options ?: throw SetupLanguageMissingException())
    }
    return translator
}
