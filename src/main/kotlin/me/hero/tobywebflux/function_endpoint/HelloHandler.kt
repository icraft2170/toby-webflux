package me.hero.tobywebflux.function_endpoint

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class HelloHandler {
    fun sayHello(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .bodyValue("Hello Webflux")
}