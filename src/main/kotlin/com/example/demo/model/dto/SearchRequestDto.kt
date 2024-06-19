package com.example.demo.model.dto

data class SearchRequestDto(
    val filters: List<SearchFiler>,
    val operation: Operation
)

enum class Operation {
    AND, OR
}