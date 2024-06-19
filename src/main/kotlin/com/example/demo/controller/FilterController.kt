package com.example.demo.controller

import com.example.demo.model.Student
import com.example.demo.model.dto.SearchRequestDto
import com.example.demo.repository.StudentRepository
import com.example.demo.service.FilterSpecification
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/filter")
class FilterController(
    val studentRepository: StudentRepository,
    val filterSpecification: FilterSpecification<Student>
) {

    @PostMapping
    fun search(@RequestBody searchRequestDto: SearchRequestDto): List<Student> {
        val specification =
            filterSpecification.getSearchSpecification(searchRequestDto.filters, searchRequestDto.operation)
        return studentRepository.findAll(specification);
    }
}
