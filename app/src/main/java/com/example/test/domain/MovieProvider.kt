package com.example.test.domain

import com.example.test.domain.model.Movie

interface MovieProvider {
    suspend fun getRandomMovie(): Result<Movie>
}