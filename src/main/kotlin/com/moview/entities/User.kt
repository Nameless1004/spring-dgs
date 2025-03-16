package com.moview.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val username: String? = null,

    @Column(nullable = false)
    val email: String? = null,

    @OneToMany(mappedBy = "user")
    val reviews: List<Review> = emptyList(),
) {
}