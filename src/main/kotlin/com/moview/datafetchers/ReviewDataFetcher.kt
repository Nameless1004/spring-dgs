package com.moview.datafetchers

import com.moview.entities.Review
import com.moview.repository.ReviewRepository
import com.moview.types.AddReviewInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsSubscription
import com.netflix.graphql.dgs.InputArgument
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.util.concurrent.Queues

@DgsComponent
class ReviewDataFetcher(
    private val userDataFetcher: UserDataFetcher,
    private val reviewRepository: ReviewRepository,
    private val movieDataFetcher: MovieDataFetcher
){

    @DgsMutation
    fun addReview(
        @InputArgument input: AddReviewInput
    ): Review {
        val user = userDataFetcher.user(input.userId)
        val movie = movieDataFetcher.movie(input.movieId)
        val review = Review(
            rating =  input.rating,
            comment = input.comment,
            user = user,
            movie = movie,
        )

        reviewRepository.save(review)
        reviewSink.tryEmitNext(review)

        return review;
    }

    private val reviewSink = Sinks
        .many()
        .multicast()
        .onBackpressureBuffer<Review>(Queues.SMALL_BUFFER_SIZE, false)
    @DgsSubscription
    fun newReview(
        @InputArgument movieId: Long
    ): Flux<Review> {
        return reviewSink.asFlux()
            .filter {it.movie?.id == movieId}
    }
}