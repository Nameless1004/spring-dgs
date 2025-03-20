package com.moview.datafetchers

import com.moview.entities.User
import com.moview.repository.UserRepository
import com.moview.types.AddUserInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
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

    @DgsMutation
    fun addUser(
        @InputArgument input: AddUserInput): User {
        val user = User(
            username = input.username,
            email = input.email
        )

        return userRepository.save(user)
    }
}