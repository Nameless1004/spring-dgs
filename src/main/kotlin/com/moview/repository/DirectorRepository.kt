package com.moview.repository

import com.moview.entities.Director
import org.springframework.data.jpa.repository.JpaRepository

interface DirectorRepository: JpaRepository<Director, Long> {
}