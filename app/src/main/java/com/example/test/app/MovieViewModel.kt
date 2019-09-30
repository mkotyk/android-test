package com.example.test.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.HttpMovieProvider
import com.example.test.domain.BaseUseCase
import com.example.test.domain.RandomMovie
import com.example.test.domain.Result
import com.example.test.domain.model.Movie

class MovieViewModel : ViewModel() {
    val movie = MutableLiveData<Movie>()
    val error = MutableLiveData<String>()

    init {
        RandomMovie(HttpMovieProvider()).invoke(
            viewModelScope,
            BaseUseCase.EmptyParams()
        ) { result ->
            when (result) {
                is Result.Success<Movie> -> this.movie.value = result.value
                is Result.Failure<Movie> -> this.error.value = result.ex.toString()
            }
        }
    }
}