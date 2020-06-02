package com.paulacr.wordslearning.feature.translation

import com.paulacr.wordslearning.data.Language
import io.reactivex.Observable

class TranslateWordRepositoryImpl(private val remoteDataSource: RemoteTranslateWordDataSource) :
    TranslateWordRepository {
    override fun subscribeToTranslator(): Observable<String> =
        remoteDataSource.subscribeToTranslator()

    override fun subscribeToDownloadLanguages(): Observable<Unit> = remoteDataSource.subscribeToDownloadLanguages()

    override fun translateWord(from: Language, to: Language, word: String) =
        remoteDataSource.translateWord(from, to, word)
}
