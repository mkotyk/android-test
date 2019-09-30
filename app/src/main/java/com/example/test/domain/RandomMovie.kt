package com.example.test.domain

import com.example.test.domain.model.Movie

class RandomMovie(private val movieProvider: MovieProvider) :
    BaseUseCase<Movie, BaseUseCase.EmptyParams>() {
    override suspend fun run(params: EmptyParams): Result<Movie> =
        this.movieProvider.getRandomMovie()

}