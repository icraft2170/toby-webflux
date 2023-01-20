package me.hero.tobywebflux.fastcampus_function_endpoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FastCampusWebFluxApplication {
    @Bean
    fun nettyReactiveWebServerFactory(): NettyReactiveWebServerFactory = NettyReactiveWebServerFactory()
}

fun main(args: Array<String>) {
    runApplication<FastCampusWebFluxApplication>(*args)
}
