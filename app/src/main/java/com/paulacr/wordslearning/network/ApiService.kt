package com.paulacr.wordslearning.network

import com.paulacr.wordslearning.data.Translations
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/translate")
    fun translateWord(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("text") text: String
    ): Single<Translations>
}
