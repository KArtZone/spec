package com.example.demo.model.dto

data class SearchFiler(
    val column: String,
    val value: String,
    val operator: Operator
)

enum class Operator {
    EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN
}