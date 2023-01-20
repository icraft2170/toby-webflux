package me.hero.tobywebflux.r2dbc

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookRepository : ReactiveCrudRepository<Book, Long> {
    /**
     * 비동기이기 떄문에 Mono 혹은 Flux 컨테이너 사용이 필요함
     */
    fun findByName(name: String): Mono<Book>
}