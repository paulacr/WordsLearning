package com.paulacr.wordslearning.network

import com.paulacr.wordslearning.data.Language
import com.paulacr.wordslearning.data.Translations
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/translate")
    fun translateWord(
        @Query("from") from: Language,
        @Query("to") to: Language,
        @Query("text") text: String
    ): Single<Translations>
}
