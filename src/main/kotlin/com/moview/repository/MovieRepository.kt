package com.moview.repository

import com.moview.entities.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<Movie, Long> {
}