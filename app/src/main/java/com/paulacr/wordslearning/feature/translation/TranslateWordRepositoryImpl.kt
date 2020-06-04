package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.common.DownloadLanguageSharedPrefs
import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

class TranslateWordRepositoryImpl(
    private val remoteDataSource: RemoteTranslateWordDataSource,
    private val localDataSource: LocalTranslateWordDataSource,
    private val downloadLanguageSharedPrefs: DownloadLanguageSharedPrefs
) :
    TranslateWordRepository {
    override fun getTranslateSubject(): Observable<String> =
        localDataSource.getTranslateSubject()

    override fun getDownloadSubject(): Observable<Pair<Language, Language>> =
        remoteDataSource.getDownloadSubject()

    override fun translateWord(from: Language, to: Language, word: String) =
        localDataSource.translateWord(from, to, word)

    override fun hasDownloadedLanguages(): Boolean =
        downloadLanguageSharedPrefs.hasDownloadedLanguages() ?: false

    override fun downloadLanguage(from: Language, to: Language) {
        downloadLanguageSharedPrefs.setDownloadedLanguages().also {
            remoteDataSource.downloadLanguage(from, to)
        }
    }
}
