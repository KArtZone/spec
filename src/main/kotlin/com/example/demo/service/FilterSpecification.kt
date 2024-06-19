package com.example.demo.service

import com.example.demo.model.dto.Operation
import com.example.demo.model.dto.Operator
import com.example.demo.model.dto.SearchFiler
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

interface FilterSpecification<T> {

    fun getSearchSpecification(
        searchFilerList: List<SearchFiler>,
        operation: Operation
    ): Specification<T>
}

@Service
class FilterSpecificationImpl<T> : FilterSpecification<T> {

    override fun getSearchSpecification(
        searchFilerList: List<SearchFiler>,
        operation: Operation
    ): Specification<T> {
        return Specification<T> { root, _, builder ->
            val predicates: MutableList<Predicate> = mutableListOf()

            for (searchFiler: SearchFiler in searchFilerList) {
                predicates += when (searchFiler.operator) {
                    Operator.EQUAL -> builder.equal(root.get<String>(searchFiler.column), searchFiler.value)
                    Operator.LIKE -> builder.like(root.get(searchFiler.column), "%${searchFiler.value}%")
                    Operator.IN -> {
                        val split = searchFiler.value.split(",").map { it.trim() }.toList()
                        root.get<String>(searchFiler.column).`in`(split)
                    }

                    Operator.GREATER_THAN -> builder.greaterThan(root.get(searchFiler.column), searchFiler.value)
                    Operator.LESS_THAN -> builder.lessThan(root.get(searchFiler.column), searchFiler.value)
                    Operator.BETWEEN -> {
                        val split = searchFiler.value.split(",").map { it.trim() }
                        builder.between(root.get(searchFiler.column), split[0], split[1])
                    }
                }
            }

            if (operation == Operation.AND) {
                builder.and(*predicates.toTypedArray())
            } else {
                builder.or(*predicates.toTypedArray())
            }
        }
    }
}