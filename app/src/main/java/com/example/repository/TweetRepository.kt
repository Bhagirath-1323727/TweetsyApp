package com.example.repository

import androidx.compose.runtime.mutableFloatStateOf
import com.example.api.TweetsyApi
import com.example.model.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject


class TweetRepository @Inject constructor(private val tweetRepository: TweetsyApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets


    suspend fun getCategory() {
        val res = tweetRepository.getCategories()
        if (res.isSuccessful && res.body() != null) {
            _categories.emit(res.body()!!.distinct())
        }
    }
    suspend fun getTweets(category:String) {
        val res = tweetRepository.getTweets("programming_tweets[?(@.category==\"$category\")]")
        if (res.isSuccessful && res.body() != null) {
            _tweets.emit(res.body()!!)
        }
    }
}
