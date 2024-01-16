package com.api

import com.example.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApi {

    @GET("/v3/b/65a12117dc746540189123b6?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>


    @GET("/v3/b/65a12117dc746540189123b6?meta=false")
    @Headers("X-JSON-Path:programming_tweets..category")
    suspend fun getCategories(): Response<List<String>>
}