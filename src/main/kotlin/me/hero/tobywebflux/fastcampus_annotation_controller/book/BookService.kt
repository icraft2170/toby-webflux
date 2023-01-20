package me.hero.tobywebflux.fastcampus_annotation_controller.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicInteger

data class Book(val id: Int, val name: String, val price: Int)

@Service
class BookService {

    private final val nextId = AtomicInteger(0)
    val books: MutableList<Book> = mutableListOf<Book>(
        Book(id = nextId.incrementAndGet(), name = "Kotlin In Action", price = 30_000),
        Book(id = nextId.incrementAndGet(), name = "HTTP완벽 가이드", price = 40_000),
    )
    fun getAll(): Flux<Book> {
        return books.toFlux()
    }

    fun get(bookId: Int): Mono<Book> =
         books.find { it.id == bookId }.toMono()

    fun add(request: Map<String, Any>): Mono<Book> =
        Mono.just(request).map {map ->
            val book = Book(
                id = nextId.incrementAndGet(),
                name = map["name"].toString(),
                price = map["price"] as Int,
            )
            books.add(book)
            book
        }

    fun delete(bookId: Int): Mono<Void> =
        Mono.justOrEmpty(
            books.find { it.id == bookId }
        )
         .map { books.remove(it) }
        .then()
}