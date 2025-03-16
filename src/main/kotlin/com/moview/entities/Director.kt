package com.moview.entities

import jakarta.persistence.*

@Entity
class Director(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String?,

    @OneToMany(mappedBy = "director")
    val movies: List<Movie> = emptyList(),
) {
}