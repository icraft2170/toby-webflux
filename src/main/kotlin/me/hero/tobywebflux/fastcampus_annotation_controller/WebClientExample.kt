package me.hero.tobywebflux.fastcampus_annotation_controller

import me.hero.tobywebflux.fastcampus_annotation_controller.book.Book
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux

@RestController
class WebClientExample {
    val url = "http://localhost:8080/books"
    val log = LoggerFactory.getLogger(javaClass)


    /**
     * RestTemplate을 사용해 Blocking으로 작동하고 순서가 보장되 디버깅이 쉽다.
     * 다만, Blocking이기 때문에 성능적 이슈를 유발할 수 있다.
     * 기본적으로 웹플럭스를 사용할 거라면 모든 지점이 논블로킹이어야 의미있다.
     */
    @GetMapping("/books/block")
    fun getBooksBlockingWay(): List<Book> {
        log.info("Start RestTemplate")
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(url, HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<Book>>() {})

        val result = response.body!!
        log.info("result : {}", result)
        log.info("Finish RestTemplate")
        return result
    }


    @GetMapping("/books/nonblock")
    fun getBooksNonBlockingWay(): Flux<Book> {
        log.info("Start WebClient")
        val books = WebClient.create()
            .get()
            .uri(url)
            .retrieve()
            .bodyToFlux<Book>()
            .map {
                log.info("result : {}", it)
                it
            }

        log.info("Finish WebClient")
        return books
    }

}