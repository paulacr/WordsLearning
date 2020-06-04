package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.common.DownloadLanguageSharedPrefs
import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

class TranslateWordRepositoryImpl(
    private val remoteDataSource: RemoteTranslateWordDataSource,
    private val downloadLanguageSharedPrefs: DownloadLanguageSharedPrefs
) :
    TranslateWordRepository {
    override fun subscribeToTranslator(): Observable<String> =
        remoteDataSource.subscribeToTranslator()

    override fun subscribeToDownloadLanguages(): Observable<Pair<Language, Language>> {
        return remoteDataSource.subscribeToDownloadLanguages()
    }

    override fun translateWord(from: Language, to: Language, word: String) =
        remoteDataSource.translateWord(from, to, word)

    override fun hasDownloadedLanguages(): Boolean =
        downloadLanguageSharedPrefs.hasDownloadedLanguages() ?: false

    override fun downloadLanguage(from: Language, to: Language) {
        downloadLanguageSharedPrefs.setDownloadedLanguages().also {
            remoteDataSource.downloadLanguage(from, to)
        }
    }
}
