package com.paulacr.wordslearning.network

import com.paulacr.wordslearning.data.Repo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{user}/repos")
    fun listAll(@Path("user") userName: String): Single<List<Repo>>
}
