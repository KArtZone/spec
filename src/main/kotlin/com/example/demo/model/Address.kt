package com.example.demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "address")
data class Address(
    @Id val id: UUID,

    @Column
    val city: String,
)