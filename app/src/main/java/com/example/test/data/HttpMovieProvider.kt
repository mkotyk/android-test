package com.example.test.data

import com.example.test.domain.MovieProvider
import com.example.test.domain.Result
import com.example.test.domain.model.Movie
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class HttpMovieProvider : MovieProvider {
    val okHttp = OkHttpClient()
    val gson = Gson()

    override suspend fun getRandomMovie(): Result<Movie> {
        val request = Request.Builder()
            .url("https://api.reelgood.com/v1/roulette/netflix?nocache=true&kind=2&minimumScore=4&availability=onAnySource")
            .build()

        return this.okHttp.newCall(request).async { response ->
            response.body?.let { body ->
                Result.Success(this.gson.fromJson<Movie>(body.charStream(), Movie::class.java))
            } ?: let {
                Result.Failure<Movie>(Exception("Unable to parse Movie Response"))
            }
        }
    }
}