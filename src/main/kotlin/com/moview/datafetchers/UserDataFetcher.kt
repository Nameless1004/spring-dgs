package com.moview.datafetchers

import com.moview.entities.User
import com.moview.repository.UserRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class UserDataFetcher(
    private val userRepository: UserRepository
) {

    @DgsQuery
    fun user(
        @InputArgument userId: Long,
    ): User {
        return userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found!") }
    }
}