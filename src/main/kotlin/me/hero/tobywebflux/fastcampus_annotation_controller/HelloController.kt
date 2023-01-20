package me.hero.tobywebflux.fastcampus_annotation_controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping
    fun hello() = "Hello WebFlux"

}