package com.moview.datafetchers

import com.moview.entities.Movie
import com.moview.repository.MovieRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class MovieDataFetcher(
    private val movieRepository: MovieRepository,
) {

    @DgsQuery
    fun movies(): MutableList<Movie> {
        return movieRepository.findAll()
    }

    @DgsQuery
    fun movie(
        @InputArgument movieId: Long
    ): Movie {
        return movieRepository.findById(movieId)
            .orElseThrow { RuntimeException("Movie not found!") }
    }
}