package me.hero.tobywebflux.fastcampus_function_endpoint

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class User(val id: Long, val email: String)

@Component
class UserHandler {

    val users = listOf<User>(
        User(id = 1, email = "user1@gmail.com"),
        User(id = 2, email = "user2@gmail.com"),
    )

    /**
     * - ServerRequest 와 ServerResponse 는 불변객체
     * - 응답은 ServerResponse 를 Mono or Flux 컨테이너로 감싸주어야
     */
    fun getUser(
        request: ServerRequest,
    ): Mono<ServerResponse> =
        users.find { request.pathVariable("id").toLong() == it.id }
            ?.let {
                ServerResponse.ok().bodyValue(it)
            } ?: ServerResponse.notFound().build()


    fun getAll(
        request: ServerRequest,
    ): Mono<ServerResponse> =
        ServerResponse.ok().bodyValue(users)
}