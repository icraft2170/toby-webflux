package me.hero.tobywebflux.annotation_controller.book

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookController(
    private val bookService: BookService,
) {

    @PostMapping("/books")
    fun add(
        @RequestBody request: Map<String, Any>
    ): Mono<Book> = bookService.add(request)

    @GetMapping("/books")
    fun getAll(): Flux<Book> = bookService.getAll()

    @GetMapping("/books/{bookId}")
    fun get(
        @PathVariable bookId: Int
    ): Mono<Book> = bookService.get(bookId)

    @DeleteMapping("/books/{bookId}")
    fun delete(
        @PathVariable bookId: Int
    ): Mono<Void> = bookService.delete(bookId)


}