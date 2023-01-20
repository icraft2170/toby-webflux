package me.hero.tobywebflux.fastcampus

import me.hero.tobywebflux.util.Logger.log
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@SpringBootApplication
class FastCampusWebFluxApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()
}

fun main(args: Array<String>) {
    runApplication<FastCampusWebFluxApplication>(*args)
}
