package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.util.*

@Entity(name = "student")
data class Student(
    @Id val id: UUID,

    @Column
    val firstName: String,

    @Column
    val lastName: String,

    @Column
    val age: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    val address: Address,

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val subjects: Set<Subject> = setOf(),
)